package com.chelab.tulsa

import com.chelab.tulsa.lookup.Family
import org.activiti.engine.history.HistoricTaskInstance

class Sample {

    def preparativeProcessService

    String processInstanceId

    String sampleId
    String description
    Date startDate
    Family family

    static transients = ['active', 'historicTasks']

    static constraints = {

    }

    boolean isActive() {
        preparativeProcessService.isActive(this)
    }

    List<HistoricTaskInstance> getHistoricTasks() {
        preparativeProcessService.getHistoricTasks(processInstanceId)
    }
}
