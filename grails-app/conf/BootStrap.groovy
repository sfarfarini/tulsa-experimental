import com.chelab.tulsa.auth.*
import com.chelab.tulsa.security.SecurityService
import org.activiti.engine.RepositoryService
import com.chelab.tulsa.lookup.Family
import com.chelab.tulsa.lookup.Family.FamilyType
import org.grails.datastore.mapping.keyvalue.mapping.config.Family
import com.chelab.tulsa.lookup.Family
import com.chelab.tulsa.Sample

class BootStrap {

    SecurityService securityService
    RepositoryService repositoryService

    def init = { servletContext ->

        assertTulsaProcessDeployed()
        addSomeUsersAndRoles()
        addSomeSamples()
    }

    private assertTulsaProcessDeployed() {
        assert repositoryService.createProcessDefinitionQuery().processDefinitionKey('pesticide').count() > 0
    }

    private void addSomeSamples() {
        createSample('pesticide', '12.000001.001', 'AAAA', new Date(), createFamily('AA', FamilyType.MACRO))
    }

    private void addSomeUsersAndRoles() {
        createRole('ROLE_OPERATOR')
        createRole('ROLE_ADMIN')
        createUser('ats', 'ats', ['ROLE_OPERATOR', 'ROLE_ADMIN'])
        createUser('operator', 'operator', ['ROLE_OPERATOR', 'ROLE_ADMIN'])
        createUser('manager', 'manager', ['ROLE_ADMIN'])
    }

    private createSample(String pId, String sampleId, String desc, Date date, Family family) {
        new Sample(processInstanceId: pId, sampleId: sampleId, description: desc, startDate: date, family: family).save(failOnError: true)
    }

    private createFamily(String description, FamilyType type) {
        new Family(description: description, familyType: type).save(failOnError: true)
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
