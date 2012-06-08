package com.chelab.tulsa.security

import grails.plugins.springsecurity.SpringSecurityService
import org.activiti.engine.IdentityService
import com.chelab.tulsa.auth.UserRole
import com.chelab.tulsa.auth.Role
import com.chelab.tulsa.auth.User

class SecurityService {

    static transactional = false

    SpringSecurityService springSecurityService
    IdentityService identityService

    User getLoggedInUser() {
        User.get(springSecurityService.principal?.id)
    }

    String getUsername() {
        loggedInUser?.username
    }

    boolean isLoggedIn() {
        springSecurityService.isLoggedIn()
    }

    void saveUser(User user) {
        user.save(failOnError: true)
        def activitiUser = identityService.newUser(user.username)
        identityService.saveUser(activitiUser)
    }

    void deleteUser(def id) {
        User user = User.get(id)
        if (user) {
            identityService.deleteUser(user.username)
            user.delete(flush: true)
        }
    }

    void saveRole(Role role) {
        role.save(failOnError: true)
        def activitiGroup = identityService.newGroup(role.authority)
        identityService.saveGroup(activitiGroup)
    }

    void deleteRole(def id) {
        Role role = Role.get(id)
        if (role) {
            identityService.deleteGroup(role.authority)
            role.delete(flush: true)
        }
    }

    void newMemberShip(User user, Role role) {
        new UserRole(user: user, role: role).save(failOnError: true)
        identityService.createMembership(user.username, role.authority)
    }

    void deleteMemberShip(User user, Role role) {
        UserRole.findByUserAndRole(user, role)?.delete(flush: true)
        identityService.deleteMembership(user.username, role.authority)
    }
}

