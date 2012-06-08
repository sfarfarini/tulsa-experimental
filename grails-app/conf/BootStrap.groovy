import com.chelab.tulsa.auth.*
import com.chelab.tulsa.security.SecurityService
import org.activiti.engine.RepositoryService

class BootStrap {

    SecurityService securityService
    RepositoryService repositoryService

    def init = { servletContext ->

        assertTulsaProcessDeployed()
        addSomeUsersAndRoles()
    }

    private assertTulsaProcessDeployed() {
        assert repositoryService.createProcessDefinitionQuery().processDefinitionKey('pesticide').count() > 0
    }

    private void addSomeUsersAndRoles() {
        createRole('ROLE_OPERATOR')
        createRole('ROLE_ADMIN')
        createUser('ats', 'ats', ['ROLE_OPERATOR', 'ROLE_ADMIN'])
        createUser('operator', 'operator', ['ROLE_OPERATOR', 'ROLE_ADMIN'])
        createUser('manager', 'manager', ['ROLE_ADMIN'])
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

    def destroy = {

    }

}
