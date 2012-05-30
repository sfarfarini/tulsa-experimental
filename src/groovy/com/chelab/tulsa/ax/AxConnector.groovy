package com.chelab.tulsa.ax

interface AxConnector {
    /**
     * Attempts a login on the AX server.<br/>
     * <strong>NOTE:</strong> implementations should <b>NOT</b> throw exceptions
     * if the login failed for some reason, rather they must return a <tt>false</tt>
     * value and handle the exception.
     *
     * @param username
     * @param password
     * @return <tt>true</tt> if the user credentials match according to AX,
     * false if they do not or if there is an exception
     */
    boolean login(String username, String password)

    List dispositionList(String organizationUnitId, Long fromDate, Long toDate, String flagDisp)

    List resultData(String dispositionId, String sampleId, String organizationUnitId, Boolean checkPresence, Integer order)
}