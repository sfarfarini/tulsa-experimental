package com.chelab.tulsa.auth

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class LogoutController {

	/**
	 * Index action. Redirects to the Spring com.chelab.tulsa.security logout uri.
	 */
	def index = {
		// TODO put any pre-logout code here
		redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl // '/j_spring_security_logout'
	}
}
