package com.chelab.activiti

import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.plugins.springsecurity.SecurityRequestHolder
import org.grails.activiti.ActivitiConstants
import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent

class InteractiveAuthenticationSuccessEventListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    GrailsApplication grailsApplication

    void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
        def sessionUsernameKey = grailsApplication.config.activiti.sessionUsernameKey ?: ActivitiConstants.DEFAULT_SESSION_USERNAME_KEY
        def session = SecurityRequestHolder.request.getSession(true)
        session[sessionUsernameKey] = event.authentication.name
    }
}
