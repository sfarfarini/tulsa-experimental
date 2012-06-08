<%@ page import="com.chelab.tulsa.Sample" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'sample.label', default: 'Sample')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-sample" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                             default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-sample" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list sample">

        <g:if test="${sampleInstance?.description}">
            <li class="fieldcontain">
                <span id="description-label" class="property-label"><g:message code="sample.description.label"
                                                                               default="Description"/></span>

                <span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${sampleInstance}"
                                                                                               field="description"/></span>

            </li>
        </g:if>

        <g:if test="${sampleInstance?.family}">
            <li class="fieldcontain">
                <span id="family-label" class="property-label"><g:message code="sample.family.label"
                                                                          default="Family"/></span>

                <span class="property-value" aria-labelledby="family-label"><g:link controller="family" action="show"
                                                                                    id="${sampleInstance?.family?.id}">${sampleInstance?.family?.encodeAsHTML()}</g:link></span>

            </li>
        </g:if>

        <g:if test="${sampleInstance?.processInstanceId}">
            <li class="fieldcontain">
                <span id="processInstanceId-label" class="property-label"><g:message
                        code="sample.processInstanceId.label" default="Process Instance Id"/></span>

                <span class="property-value" aria-labelledby="processInstanceId-label"><g:fieldValue
                        bean="${sampleInstance}" field="processInstanceId"/></span>

            </li>
        </g:if>

        <g:if test="${sampleInstance?.sampleId}">
            <li class="fieldcontain">
                <span id="sampleId-label" class="property-label"><g:message code="sample.sampleId.label"
                                                                            default="Sample Id"/></span>

                <span class="property-value" aria-labelledby="sampleId-label"><g:fieldValue bean="${sampleInstance}"
                                                                                            field="sampleId"/></span>

            </li>
        </g:if>

        <g:if test="${sampleInstance?.startDate}">
            <li class="fieldcontain">
                <span id="startDate-label" class="property-label"><g:message code="sample.startDate.label"
                                                                             default="Start Date"/></span>

                <span class="property-value" aria-labelledby="startDate-label"><g:formatDate
                        date="${sampleInstance?.startDate}"/></span>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${sampleInstance?.id}"/>
            <g:link class="edit" action="edit" id="${sampleInstance?.id}"><g:message code="default.button.edit.label"
                                                                                     default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
