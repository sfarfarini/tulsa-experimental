import com.chelab.tulsa.auth.Role
import com.chelab.tulsa.auth.SecurityService
import com.chelab.tulsa.auth.User
import org.activiti.engine.ProcessEngine
import org.activiti.engine.RepositoryService

class BootStrap {

    RepositoryService repositoryService
    ProcessEngine processEngine
    SecurityService securityService

    def init = { servletContext ->

        def ctx = servletContext.getAttribute(org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes.APPLICATION_CONTEXT);

        createRole('ROLE_ATS')
        createRole('ROLE_USER')
        createRole('ROLE_MANAGER')
        createUser('ats', 'ats', ['ROLE_ATS'])
        createUser('technician', 'technician', ['ROLE_USER'])
        createUser('manager', 'manager', ['ROLE_MANAGER'])
    }

    def destroy = {
        processEngine.close()
    }

    private createRole(String authority) {
        if (Role.countByAuthority(authority) == 0) {
            securityService.saveRole(new Role(authority: authority))
        }
    }

    private createUser(String username, String password, List<String> authorities) {
        if (User.countByUsername(username) == 0) {
            User user = new User(username: username, password: password, enabled: true)
            securityService.saveUser(user)
            authorities.each {
                securityService.newMemberShip(user, Role.findByAuthority(it))
            }
        }
    }
}
