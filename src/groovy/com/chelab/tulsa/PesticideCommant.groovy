package com.chelab.tulsa

import grails.validation.Validateable

@Validateable
class PesticideCommand {

    String taskId

    Sample sample
}
