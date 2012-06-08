package com.chelab.tulsa.lookup

class Family {

    static enum FamilyType {
        MICRO, MACRO
    }

    String description
    FamilyType familyType

    static constraints = {
        description(nullable: true, blank: true)
        familyType(nullable: true, blank: true)
    }
}
