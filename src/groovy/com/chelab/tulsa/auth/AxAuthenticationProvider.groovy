package com.chelab.tulsa.auth

import com.chelab.tulsa.ax.AxConnector
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication

class AxAuthenticationProvider implements AuthenticationProvider {
    private static final Logger LOG = LoggerFactory.getLogger(AxAuthenticationProvider)
    AxConnector axConnector

    Authentication authenticate(Authentication authentication) {
        if(LOG.debugEnabled) {
            LOG.debug("Authenticating ${authentication.principal}")
        }
        Authentication token = new UsernamePasswordAuthenticationToken(authentication.principal, authentication.credentials)
        if (axConnector.login(authentication.principal.toString(), authentication.credentials.toString())) {
            if(LOG.debugEnabled) {
                LOG.debug("Authentication of ${authentication.principal} was successful")
            }
            User.withTransaction {
                User user = User.findByUsername(authentication.principal.toString())
                if (user) {
                    token = new UsernamePasswordAuthenticationToken(
                            authentication.principal,
                            authentication.credentials,
                            user.authorities)
                }
            }
        }  else {
            throw new BadCredentialsException("AxAuthenticationProvider failed to authenticate ${authentication.principal}")
        }
        token
    }

    boolean supports(Class<? extends Object> aClass) {
        true
    }
}
