

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title></title>
</head>
<body>



    <g:form action="search">
        <g:textField name="barcode" value=""/>
        <g:submitButton name="submit" value="Search"/>
    </g:form>

    <g:if test="${barcodeNew}">
        <g:link action="create" params="${[sampleId:barcodeNew]}">Crete sample for ${barcodeNew}</g:link>
    </g:if>

</body>
</html>