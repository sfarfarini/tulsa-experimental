import grails.util.Environment

// Place your Spring DSL code here
beans = {
    if (Environment.current == Environment.PRODUCTION || Environment.current == Environment.DEVELOPMENT || application.config.axconnector.live) {
        axConnector(com.chelab.tulsa.ax.LiveAxConnector) {
            grailsApplication = ref('grailsApplication')
        }
    } else {
        axConnector(com.chelab.tulsa.ax.InMemoryAxConnector)
    }

    axAuthenticationProvider(com.chelab.tulsa.auth.AxAuthenticationProvider) {
        axConnector = ref('axConnector')
    }
}
