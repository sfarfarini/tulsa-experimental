package com.chelab.tulsa.core

class Falcon {

    String barcode
    List<String> requiredAnalysis

    static hasMany = [vial: Vial]

    static constraints = {

    }
}
