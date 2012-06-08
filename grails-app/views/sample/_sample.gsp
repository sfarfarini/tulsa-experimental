<%@ page import="com.chelab.tulsa.Sample" %>

<div class="section">
    <div class="sectionTitle">
        <g:message code="sample.label"/>
    </div>

    <div class="fieldcontain">
        <label>
            <g:message code="sample.sampleId.label"/>
        </label>
        <g:link controller="sample" action="show" id="${sample?.id}">
            <g:fieldValue field="sampleId" bean="${sample}"/>
        </g:link>
    </div>
</div>
