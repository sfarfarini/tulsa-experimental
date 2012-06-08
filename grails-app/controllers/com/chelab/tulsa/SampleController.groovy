package com.chelab.tulsa

import grails.validation.ValidationException
import org.activiti.engine.ActivitiException

class SampleController {

    PesticideProcessService processService

    static scaffold = Sample

    def save = {

        Sample sample = new Sample(params)

        try {
            processService.start(sample)
        } catch (ValidationException ignored) {
            render(view: 'create', model: [sample: sample])
            return
        } catch (ActivitiException ignored) {
            flash.message = 'could not start new process. Try again'
            render(view: 'create', model: [sample: sample])
            return
        }
        flash.message = "started new process for $sample"
        redirect(action: 'show', id: sample.id)
    }

/*    def index() {
        redirect(action: "list", params: params)
    }*/

    /*def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [sampleInstanceList: Sample.list(params), sampleInstanceTotal: Sample.count()]
    }*/

    /*def create() {
        [sampleInstance: new Sample(params)]
    }*/

    /*def save() {
        def sampleInstance = new Sample(params)
        if (!sampleInstance.save(flush: true)) {
            render(view: "create", model: [sampleInstance: sampleInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'sample.label', default: 'Sample'), sampleInstance.id])
        redirect(action: "show", id: sampleInstance.id)
    }
*/
    /*def show() {
        def sampleInstance = Sample.get(params.id)
        if (!sampleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sample.label', default: 'Sample'), params.id])
            redirect(action: "list")
            return
        }

        [sampleInstance: sampleInstance]
    }

    def edit() {
        def sampleInstance = Sample.get(params.id)
        if (!sampleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sample.label', default: 'Sample'), params.id])
            redirect(action: "list")
            return
        }

        [sampleInstance: sampleInstance]
    }

    def update() {
        def sampleInstance = Sample.get(params.id)
        if (!sampleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sample.label', default: 'Sample'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (sampleInstance.version > version) {
                sampleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'sample.label', default: 'Sample')] as Object[],
                        "Another user has updated this Sample while you were editing")
                render(view: "edit", model: [sampleInstance: sampleInstance])
                return
            }
        }

        sampleInstance.properties = params

        if (!sampleInstance.save(flush: true)) {
            render(view: "edit", model: [sampleInstance: sampleInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'sample.label', default: 'Sample'), sampleInstance.id])
        redirect(action: "show", id: sampleInstance.id)
    }

    def delete() {
        def sampleInstance = Sample.get(params.id)
        if (!sampleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sample.label', default: 'Sample'), params.id])
            redirect(action: "list")
            return
        }

        try {
            sampleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'sample.label', default: 'Sample'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'sample.label', default: 'Sample'), params.id])
            redirect(action: "show", id: params.id)
        }
    }*/
}
