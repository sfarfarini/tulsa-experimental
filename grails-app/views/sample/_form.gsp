<%@ page import="com.chelab.tulsa.Sample" %>



<div class="fieldcontain ${hasErrors(bean: sampleInstance, field: 'description', 'error')} ">
    <label for="description">
        <g:message code="sample.description.label" default="Description"/>

    </label>
    <g:textField name="description" value="${sampleInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sampleInstance, field: 'family', 'error')} required">
    <label for="family">
        <g:message code="sample.family.label" default="Family"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="family" name="family.id" from="${com.chelab.tulsa.lookup.Family.list()}" optionKey="id" required=""
              value="${sampleInstance?.family?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sampleInstance, field: 'processInstanceId', 'error')} ">
    <label for="processInstanceId">
        <g:message code="sample.processInstanceId.label" default="Process Instance Id"/>

    </label>
    <g:textField name="processInstanceId" value="${sampleInstance?.processInstanceId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sampleInstance, field: 'sampleId', 'error')} ">
    <label for="sampleId">
        <g:message code="sample.sampleId.label" default="Sample Id"/>

    </label>
    <g:textField name="sampleId" value="${sampleInstance?.sampleId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sampleInstance, field: 'startDate', 'error')} required">
    <label for="startDate">
        <g:message code="sample.startDate.label" default="Start Date"/>
        <span class="required-indicator">*</span>
    </label>
    <g:datePicker name="startDate" precision="day" value="${sampleInstance?.startDate}"/>
</div>

