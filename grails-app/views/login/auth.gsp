<html>
<head>
    <meta name='layout' content='bootstrap'/>
    <title><g:message code="springSecurity.login.title"/></title>

    <r:script>
        $(document).ready(function() {
            $('[name=j_username]').focus();
        });
    </r:script>
</head>

<body>
<br />
<div class="hero-unit">

    <div class="row">
        <div class="span7">
            <h1>Tulsa Fitofarma</h1>
            <br />
            <g:if test="${flash.message}">
                <bootstrap:alert class="alert alert-error">${flash.message}</bootstrap:alert>
            </g:if>
        </div>
        <div class="span3">
            <g:render template="loginForm" model="[postUrl: postUrl, rememberMeParameter: rememberMeParameter, hasCookie: hasCookie]" />
        </div>
    </div>
</div>
</body>
</html>