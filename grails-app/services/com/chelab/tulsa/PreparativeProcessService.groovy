package com.chelab.tulsa

import org.activiti.engine.runtime.ProcessInstance
import com.chelab.tulsa.auth.User
import com.chelab.tulsa.security.SecurityService
import org.activiti.engine.RuntimeService
import org.activiti.engine.task.Task
import org.activiti.engine.TaskService
import org.activiti.engine.history.HistoricTaskInstance
import org.activiti.engine.HistoryService

class PreparativeProcessService {

    static transactional = true

    SecurityService securityService
    RuntimeService runtimeService
    TaskService taskService
    HistoryService historyService

    ProcessInstance start(Sample sample) {

        User user = securityService.loggedInUser
        if (!user) {
            throw new IllegalStateException('no logged in user!')
        }

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
                'preparative', sample.sampleId, [controller: 'preparativeProcess', action: 'complete']
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

    List<Task> getTasks(Sample sample) {
        taskService.createTaskQuery().processInstanceId(sample.processInstanceId).list()
    }

    Sample getSample(String taskId) {
        Task task = getTask(taskId)
        if (!task) {
            return null
        }
        Sample.findByProcessInstanceId(task.processInstanceId)
    }

    Boolean isPossible(Task task) {
        String username = securityService.username
        if (taskService.createTaskQuery().taskId(task.id).taskAssignee(username).count() > 0 ||
                taskService.createTaskQuery().taskId(task.id).taskCandidateUser(username).count() > 0
        ) {
            return true
        }
        false
    }

    boolean isActive(Sample sample) {
        runtimeService.createProcessInstanceQuery().processInstanceId(sample.processInstanceId).count() > 0
    }

    List<HistoricTaskInstance> getHistoricTasks(String processInstanceId) {
        historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).orderByHistoricActivityInstanceStartTime().desc().list()
    }
}
