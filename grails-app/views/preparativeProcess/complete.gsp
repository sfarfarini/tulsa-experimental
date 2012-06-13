<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    %{--<g:set var="cultureMediumInfo" value="${cultureMedium?.cultureMediumInfo}"/>--}%
</head>

<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    </ul>
</div>

<div id="completeTask" class="content scaffold-create" role="main">
    <h1><g:message code="default.complete.label" args="[task.name, sample.sampleId]"/></h1>
    <g:render template="/sample/sample"/>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    %{--<g:hasErrors bean="${command}">
        <ul class="errors" role="alert">
            <g:eachError bean="${command}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>--}%
    <g:form action="${task.taskDefinitionKey}">

        <g:hiddenField name="taskId" value="${task.id}"/>
        <g:hiddenField name="sample.id" value="${sample?.id}"/>

        %{--<g:render template="taskTemplates/${task.taskDefinitionKey}"/>--}%

        <fieldset class="buttons">
            <g:submitButton name="complete" class="save"
                            value="${message(code: 'default.button.complete.label', default: 'Complete')}"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
