package com.chelab.tulsa.auth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.GrantedAuthorityImpl

class User {

    transient springSecurityService

    String username
    String password
    String name
    String axCode
    boolean enabled
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

//    void setPassword(String password) {
//        this.password = ''
//    }

    static constraints = {
        username blank: false, unique: true
        password nullable: true, blank: true
    }

    Set<GrantedAuthority> getAuthorities() {
        UserRole.findAllByUser(this).collect { new GrantedAuthorityImpl(it.role.authority) } as Set
    }

    static User findOrCreateUser(String axCode, String name, String userName){
        User user = User.findByAxCode(axCode)
        if (!user){
            user = new User(username: userName,name: name, axCode: axCode)

            if (!user.save()) {
                user.errors.each { error ->
                    log.error error.toString()
                }
            }
        }
        user
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService?.encodePassword(password)
    }
}
