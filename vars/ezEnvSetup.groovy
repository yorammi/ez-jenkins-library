def initEnv(Map config=null) {
    ezLog.anchor "ez initEnv"
    //identify()
    if (config == null) {
        config = [:]
    }
    if (config.deleteWorkspace == null) {
        config.deleteWorkspace = true
    }

    if(config.deleteWorkspace)
    {
        radcomCommon.deleteWorkspace()
    }
    initUserEnvVars()
    if(ezUtils.isBuildStartedByUser() || env.BUILD_USER != '') {
        if(env.BUILD_USER != "admin") {
            currentBuild.displayName += " {${env.BUILD_USER}}"
        }
    }
}

def initUserEnvVars() {
    wrap([$class: 'BuildUser']) {
        try {
            env.BUILD_USER_FIRST_NAME = BUILD_USER_FIRST_NAME
        }
        catch (Error) {
            env.BUILD_USER_FIRST_NAME = ""
        }
        try {
            env.BUILD_USER_LAST_NAME = BUILD_USER_LAST_NAME
        }
        catch (Error) {
            env.BUILD_USER_LAST_NAME = ""
        }
        try {
            env.BUILD_USER_ID = BUILD_USER_ID
        }
        catch (Error) {
            env.BUILD_USER_ID = "admin"
        }
        try {
            env.BUILD_USER = BUILD_USER
        }
        catch (Error) {
            env.BUILD_USER = env.BUILD_USER_ID
        }
        try {
            env.BUILD_USER_EMAIL = BUILD_USER_EMAIL
        }
        catch (Error) {
            env.BUILD_USER_EMAIL = ""
        }
        try {
            env.BUILD_USER_SLACK = BUILD_USER_SLACK
        }
        catch (Error) {
            env.BUILD_USER_EMAIL = ""
        }
    }
}