package com.chelab.tulsa.ax

class InMemoryAxConnector implements AxConnector {
    final Map<String, String> users = [:]
    
    boolean login(String username, String password) {
        users[username] == password
    }

    List dispositionList(String organizationUnitId, Long fromDate, Long toDate, String flagDisp){
        []
    }

    List resultData(String dispositionId, String sampleId, String organizationUnitId, Boolean checkPresence, Integer order){
        []
    }
}
