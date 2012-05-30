import grails.util.Environment

InetAddress addr = InetAddress.getLocalHost()
def hostIP = addr.hostAddress

environments {

    production {
        grails.serverURL = "http://${addr.hostName}:8080/${appName}"
//        ax.host = 'http://192.168.100.139/'
        ax.host = 'http://46.137.104.199/'
        grails.logging.jul.usebridge = false
    }

    development {
        grails.serverURL = "http://localhost:8080/${appName}"
        ax.host = 'http://46.137.104.199/'
        grails.logging.jul.usebridge = true
    }

    test {
        grails.serverURL = "http://localhost:8080/${appName}"
        ax.host = 'http://46.137.104.199/'
    }
}

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
        xml: ['text/xml', 'application/xml'],
        text: 'text/plain',
        js: 'text/javascript',
        rss: 'application/rss+xml',
        atom: 'application/atom+xml',
        css: 'text/css',
        csv: 'text/csv',
        all: '*/*',
        json: ['application/json','text/json'],
        form: 'application/x-www-form-urlencoded',
        multipartForm: 'multipart/form-data'
]

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'com.chelab.tulsa.auth.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'com.chelab.tulsa.auth.UserRole'
grails.plugins.springsecurity.authority.className = 'com.chelab.tulsa.auth.Role'
if (Environment.current != Environment.DEVELOPMENT) {
    grails.plugins.springsecurity.providerNames = [
            'axAuthenticationProvider'
    ]
}

grails.plugins.springsecurity.controllerAnnotations.staticRules = [
        '/': ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/login/auth': ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/js/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/css/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/images/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/**': ['IS_AUTHENTICATED_FULLY']
]

grails.gorm.default.constraints = { '*'(nullable: true) }

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']


// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// enable query caching by default
grails.hibernate.cache.queries = true

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
            'org.codehaus.groovy.grails.web.pages', //  GSP
            'org.codehaus.groovy.grails.web.sitemesh', //  layouts
            'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
            'org.codehaus.groovy.grails.web.mapping', // URL mapping
            'org.codehaus.groovy.grails.commons', // core / classloading
            'org.codehaus.groovy.grails.plugins', // plugins
            'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
            'org.springframework',
            'org.hibernate',
            'net.sf.ehcache.hibernate'
}

// Added by the Grails Activiti plugin:
activiti {
    processEngineName = "activiti-engine-default"
    databaseType = "h2"
    deploymentName = appName
    deploymentResources = ["classpath:/activiti/**/*.bpmn*.xml"]
    jobExecutorActivate = false
    mailServerHost = "smtp.yourserver.com"
    mailServerPort = "25"
    mailServerUsername = ""
    mailServerPassword = ""
    mailServerDefaultFrom = "username@yourserver.com"
    history = "audit" // "none", "activity", "audit" or "full"
    sessionUsernameKey = "username"
    useFormKey = true
}

environments {
    development {
        activiti {
            processEngineName = "activiti-engine-dev"
            databaseSchemaUpdate = true // true, false or "create-drop"
        }
    }
    test {
        activiti {
            processEngineName = "activiti-engine-test"
            databaseSchemaUpdate = true
            mailServerPort = "5025"
        }
    }
    production {
        activiti {
            processEngineName = "activiti-engine-prod"
            databaseSchemaUpdate = false
            jobExecutorActivate = true
        }
    }
}	

