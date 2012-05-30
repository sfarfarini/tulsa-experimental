package com.chelab.activiti

import org.activiti.engine.task.Task
import org.codehaus.groovy.grails.web.mapping.LinkGenerator

class ActivitiService extends org.grails.activiti.ActivitiService {

    LinkGenerator grailsLinkGenerator

    String getTaskFormUri(String taskId, boolean useFormKey) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult()
        if (task) {
            String controller = runtimeService.getVariable(task.executionId, 'controller')
            String action = runtimeService.getVariable(task.executionId, 'action')
            return grailsLinkGenerator.link(controller: controller, action: action, params: [taskId: task.id], absolute: true)
        }
        '/'
    }
}