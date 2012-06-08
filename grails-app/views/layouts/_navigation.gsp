<sec:ifLoggedIn>
    <ul id="nav">
        <li><g:link uri="/"><g:message code="menu.home.label"/></g:link></li>

        <li><a href=""><g:message code="menu.processes.label"/></a>
            <ul>
                <li>
                    <g:link controller="sample" action="create">
                        <g:message code="menu.newSample.label"/>
                    </g:link>
                </li>
                <li>
                    <g:link controller="sample" action="list">
                        <g:message code="menu.listSample.label"/>
                    </g:link>
                </li>
                <li>
                    <g:link controller="task">
                        <g:message code="menu.listTasks.label"/>
                    </g:link>
                </li>
            </ul>
        </li>

        %{--<li><g:link controller="kitchenProcess" action="showIncubators"><g:message
                code="menu.incubators.label"/></g:link></li>

        <li><a href=""><g:message code="menu.admin.label"/></a>
            <ul>
                <li>
                    <g:link controller="cultureMediumInfo" action="list">
                        <g:message code="cultureMediumInfo.label"/>
                    </g:link>
                </li>
                <li>
                    <g:link controller="incubatorInfo" action="list">
                        <g:message code="incubatorInfo.label"/>
                    </g:link>

                </li>
                <li>
                    <g:link controller="incubator" action="list">
                        <g:message code="incubator.label"/>
                    </g:link>

                </li>
                <li>
                    <g:link controller="resourceInfo" action="list">
                        <g:message code="resourceInfo.label"/>
                    </g:link>

                </li>
                <li>
                    <g:link controller="unit" action="list">
                        <g:message code="unit.label"/>
                    </g:link>
                </li>
            </ul>
        </li>
        <li>
            <g:link controller="logout" action="index">
                <g:message code="menu.logout.label" args="${[applicationContext.securityService.username]}"/>
            </g:link>
        </li>--}%
    </ul>
</sec:ifLoggedIn>
