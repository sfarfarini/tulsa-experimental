package com.chelab.tulsa

import org.activiti.engine.task.Task

class PesticideProcessController {

    PesticideProcessService pesticideProcessService

/*    def complete = {
        Task task = pesticideProcessService.getTask(params.taskId)
        Sample sample = pesticideProcessService.getSample(params.taskId)

        [sample: sample, task: task]
    }*/

    def startTaskBySampleId = {

        Sample sample = Sample.findBySampleId(params.sampleId)

        if (!sample) {
            flash.message = "${params.sampleId} does not exist!"
            redirect(uri: '/')
            return
        }

        List<Task> tasks = []

        tasks.addAll(pesticideProcessService.getTasks(sample))

        Map<Task, Boolean> taskMap = tasks.collectEntries {
            [(it): pesticideProcessService.isPossible(it)]
        }

        if (taskMap.size() == 1 && (taskMap.values() as List).first()) {
            Task task = (taskMap.keySet() as List).first()
            redirect(controller: 'task', action: 'startTask', params: [taskId: task.id])
            return
        }
        render(view: 'showTasks', model: [sample: sample, taskMap: taskMap])
    }
}
