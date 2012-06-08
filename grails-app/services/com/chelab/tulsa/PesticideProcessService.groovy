package com.chelab.tulsa

import org.activiti.engine.runtime.ProcessInstance
import com.chelab.tulsa.auth.User
import com.chelab.tulsa.security.SecurityService
import org.activiti.engine.RuntimeService
import org.activiti.engine.task.Task
import org.activiti.engine.TaskService

class PesticideProcessService {

    static transactional = true

    SecurityService securityService
    RuntimeService runtimeService
    TaskService taskService

    ProcessInstance start(Sample sample) {

        User user = securityService.loggedInUser
        if (!user) {
            throw new IllegalStateException('no logged in user!')
        }
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(
                'pesticide', sample.sampleId, [controller: 'pesticideProcess', action: 'complete']
        )
        sample.processInstanceId = processInstance.id
        sample.save(failOnError: true)
        setDomainInstance(processInstance.id, sample)
        return processInstance
    }

    private setDomainInstance(String processInstanceId, def domainInstance) {
        runtimeService.setVariable(processInstanceId, 'id', domainInstance.id)
        runtimeService.setVariable(processInstanceId, 'domainClassName', domainInstance.class.simpleName)
    }

    Task getTask(def taskId) {
        taskService.createTaskQuery().taskId(taskId).singleResult()
    }
}
