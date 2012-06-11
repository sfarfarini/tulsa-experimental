<div class="section">
    <div class="sectionTitle">
        <g:message code="sample.historicTasks.label"/>
    </div>
    <table>
        <thead>
        <tr>
            <th><g:message code="task.name.label"/></th>
            <th><g:message code="task.start.label"/></th>
            <th><g:message code="task.end.label"/></th>
            <th><g:message code="task.assignee.label"/></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${tasks}" var="task">
            <tr>
                <td><g:fieldValue field="name" bean="${task}"/></td>
                <td><g:fieldValue field="startTime" bean="${task}"/></td>
                <td><g:fieldValue field="endTime" bean="${task}"/></td>
                <td><g:fieldValue field="assignee" bean="${task}"/></td>
                <td>
                    <g:if test="${!task.endTime}">
                    %{--TODO check if the assignee fits to logged in user--}%
                        <g:link controller="task" action="startTask" params="${[taskId: task.id]}">
                            Start Task
                        </g:link>
                    </g:if>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
