package com.chelab.tulsa

class AnalysisController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    static activiti = true

    def index = {
        redirect(action: "list", params: params)
    }

    def start = {
        start(params)
    }
	
    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [analysisInstanceList: Analysis.list(params), 
			   analysisInstanceTotal: Analysis.count(),
			   myTasksCount: assignedTasksCount]
    }

    def create = {
        def analysisInstance = new Analysis()
        analysisInstance.properties = params
        return [analysisInstance: analysisInstance,
			          myTasksCount: assignedTasksCount]
    }

    def save = {
        def analysisInstance = new Analysis(params)
        if (analysisInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'analysis.label', default: 'Analysis'), analysisInstance.id])}"
			      params.id = analysisInstance.id
						if (params.complete) {
							completeTask(params)
						} else {
							params.action="show"
							saveTask(params)
						}
            redirect(action: "show", params: params)
        }
        else {
            render(view: "create", model: [analysisInstance: analysisInstance, myTasksCount: assignedTasksCount])
        }
    }

    def show = {
        def analysisInstance = Analysis.get(params.id)
        if (!analysisInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'analysis.label', default: 'Analysis'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
        else {
            [analysisInstance: analysisInstance, myTasksCount: assignedTasksCount]
        }
    }

    def edit = {
        def analysisInstance = Analysis.get(params.id)
        if (!analysisInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'analysis.label', default: 'Analysis'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
        else {
            [analysisInstance: analysisInstance, myTasksCount: assignedTasksCount]
        }
    }

    def update = {
        def analysisInstance = Analysis.get(params.id)
        if (analysisInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (analysisInstance.version > version) {
                    
                    analysisInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'analysis.label', default: 'Analysis')] as Object[], "Another user has updated this Analysis while you were editing")
                    render(view: "edit", model: [analysisInstance: analysisInstance, myTasksCount: assignedTasksCount])
                    return
                }
            }
            analysisInstance.properties = params
            if (!analysisInstance.hasErrors() && analysisInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'analysis.label', default: 'Analysis'), analysisInstance.id])}"
								Boolean isComplete = params["_action_update"].equals(message(code: 'default.button.complete.label', default: 'Complete'))
								if (isComplete) {
										completeTask(params)
								} else {
										params.action="show"
										saveTask(params)
								}				
                redirect(action: "show", id: analysisInstance.id, params: [taskId:params.taskId, complete:isComplete?:null])
            }
            else {
                render(view: "edit", model: [analysisInstance: analysisInstance, myTasksCount: assignedTasksCount])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'analysis.label', default: 'Analysis'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
    }

    def delete = {
        def analysisInstance = Analysis.get(params.id)
        if (analysisInstance) {
            try {
                analysisInstance.delete(flush: true)
                deleteTask(params.taskId)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'analysis.label', default: 'Analysis'), params.id])}"
                redirect(controller: "task", action: "myTaskList")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'analysis.label', default: 'Analysis'), params.id])}"
                redirect(action: "show", id: params.id, params: params)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'analysis.label', default: 'Analysis'), params.id])}"
            redirect(controller: "task", action: "myTaskList")
        }
    }
}
