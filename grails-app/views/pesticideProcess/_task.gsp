<div class="section">
    <div class="sectionTitle">
        <g:message code="task.label"/>
    </div>

    <div class="fieldcontain">
        <label>
            <g:message code="task.name.label"/>
        </label>
        <g:fieldValue field="name" bean="${task}"/>
    </div>

    <div class="fieldcontain">
        <label>
            <g:message code="task.description.label"/>
        </label>
        <g:fieldValue field="description" bean="${task}"/>
    </div>
    <g:if test="${taskMap[task]}">
        <div class="fieldcontain">
            <label>
                Start
            </label>
            <g:link controller="task" action="startTask" params="[taskId: task.id]">
                Click here
            </g:link>
        </div>
    </g:if>
    <g:else>
        <div class="fieldcontain">
            <label>
                Start
            </label>
            Already assigned
        </div>
    </g:else>
</div>