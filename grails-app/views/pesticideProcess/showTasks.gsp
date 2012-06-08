<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
</head>

<body>

<div id="show" class="content" role="main">
    <h1><g:message code="default.show.label" args="['Task']"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:render template="/sample/sample"/>
    <g:each in="${taskMap.keySet()}" var="task">
        <g:render template="task" model="${[task: task]}"/>
    </g:each>
</div>
</body>
</html>
