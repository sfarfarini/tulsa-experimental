package com.chelab.tulsa.ax

import groovyx.net.http.HTTPBuilder
import grails.util.GrailsUtil
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class LiveAxConnector implements AxConnector {
    private static final Logger LOG = LoggerFactory.getLogger(LiveAxConnector)

    GrailsApplication grailsApplication

    boolean login(String username, String password) {
        boolean result = false

        LOG.info "Login $username"
        ConfigObject config = grailsApplication.config
        String host = config.ax.host
        String path = "axrest/user/networkalias/${username}/password/${password}?format=json"
        HTTPBuilder http = new HTTPBuilder(host + path)

        try {
            http.request(groovyx.net.http.Method.GET, groovyx.net.http.ContentType.JSON) { req ->
                headers.Accept = 'application/json'
                headers.contentType = 'application/json'

                response.success = { resp, reader ->
                    if (resp.status == 200) {
                        result = true
                    } else if (LOG.warnEnabled) {
                        LOG.warn("Could not login with user $username")
                    }
                }

                response.'404' = {
                    if (LOG.warnEnabled) LOG.warn("Could not login with user $username")
                    result = false
                }
            }
        } catch (x) {
            if (LOG.warnEnabled) LOG.warn("Could not login with user $username", GrailsUtil.sanitize(x))
            result = false
        }

        result
    }

    List dispositionList(String organizationUnitId, Long fromDate, Long toDate, String flagDisp){

        LOG.info "Loading disposition list: organizationUnitId $organizationUnitId - fromDate $fromDate - toDate $toDate - flagDisp $flagDisp"
        ConfigObject config = grailsApplication.config
        String host = config.ax.host
        String path = "axrest/disposition/organizationUnitId/${organizationUnitId}/fromCreatedDateTime/${fromDate}/toCreatedDateTime/${toDate}/flagDisp/${flagDisp}?format=json"
        HTTPBuilder http = new HTTPBuilder(host+path)

//        Long fromDateMs = Date.getMillisOf(fromDate)
//        Long toDateMs = Date.getMillisOf(toDate)

        def json = []
        try {
            http.request(groovyx.net.http.Method.GET, groovyx.net.http.ContentType.JSON) { req ->
                headers.Accept = 'application/json'
                headers.contentType = 'application/json'

                response.success = { resp, reader ->
                    if (resp.status == 200) {
                        json = reader.dispositionList
                    } else if (LOG.warnEnabled) {
                        LOG.warn("Could not find disposition list path: ${path} status: ${resp.status}")
                    }
                }

                response.'404' = {
                    if (LOG.warnEnabled) LOG.warn("Could not find disposition list path: ${path} status: ${resp.status}")
                    json = []
                }
            }
        } catch (x) {
            if (LOG.warnEnabled) LOG.warn("Could not find disposition list path: ${path} status: ${resp.status}", GrailsUtil.sanitize(x))
            json = []
        }

        json

    }

    List resultData(String dispositionId, String sampleId, String organizationUnitId, Boolean checkPresence, Integer order){

        LOG.info "Loading result Data list: organizationUnitId $organizationUnitId - dispositionId $dispositionId - sampleId $sampleId - checkPresence $checkPresence - order $order"
        ConfigObject config = grailsApplication.config
        String host = config.ax.host
        String path
        if (sampleId)
            path = "axrest/vbinterface/dispositionId/${dispositionId}/sampleId/${sampleId}/hrmOrganizationId/${organizationUnitId}/checkPresence/${checkPresence}/order/${order}?format=json"
        else
            path = "axrest/vbinterface/dispositionId/${dispositionId}/hrmOrganizationId/${organizationUnitId}/checkPresence/${checkPresence}/order/${order}?format=json"
        HTTPBuilder http = new HTTPBuilder(host+path)

        def json = null
        try {
            http.request(groovyx.net.http.Method.GET, groovyx.net.http.ContentType.JSON) { req ->
                headers.Accept = 'application/json'

                response.success = { resp, reader ->
                    if (resp.status == 200) {
                        json = reader.vbInterfaceList
                    } else if (LOG.warnEnabled) {
                        LOG.warn("Could not find result data path: ${path} status: ${resp.status}")
                    }
                }

                response.'404' = {
                    if (LOG.warnEnabled) LOG.warn("Could not find result data path: ${path} status: ${resp.status}")
                    json = null
                }
            }
        } catch (x) {
            if (LOG.warnEnabled) LOG.warn("Could not find result data path: ${path} status: ${resp.status}", GrailsUtil.sanitize(x))
            json = null
        }

        json

    }
}
