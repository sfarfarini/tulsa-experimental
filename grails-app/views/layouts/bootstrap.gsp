<%@ page import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title><g:layoutTitle default="${meta(name: 'app.name')}"/></title>
    <meta name="description" content="">
    <meta name="author" content="">

    <meta name="viewport" content="initial-scale = 1.0">

    <script type="text/javascript" src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>

    <r:require modules="custom, scaffolding, datepicker"/>

    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">

    <r:script>

        /* Update datepicker plugin so that dd/MM/yy format is used. */
        jQuery.extend($.fn.datepicker.defaults, {

            parse : function (string) {

                var matches;
                if ((matches = string.match(/^(\d{2,2})\/(\d{2,2})\/(\d{4,4})$/))) {
                    return new Date(matches[3], matches[2] - 1, matches[1]);
                } else {
                    return null;
                }
            },

            format : function (date) {

                var month = (date.getMonth() + 1).toString();
                var dom = date.getDate().toString();

                if (month.length === 1) {
                    month = "0" + month;
                }
                if (dom.length === 1) {
                    dom = "0" + dom;
                }
                return dom + "/" + month + "/" + date.getFullYear();
            }
        });

    </r:script>

    <g:layoutHead/>
    <r:layoutResources/>
</head>

<body>

<nav class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <g:link class="brand" url="${createLink(controller: 'main', action: 'index')}">
                <g:img dir="images" file="chelab_nov11_h36.png" style="vertical-align: bottom; width: 125px;" />
                <g:message code="main.title.label" />
            </g:link>
            <ul class="nav">
                <li>
                    <g:link url="${createLink(controller: 'acceptance', action: 'index')}">
                        <g:message code="main.loadAx.label" />
                    </g:link>
                </li>
            </ul>

            <ul class="nav pull-right">
                <sec:ifLoggedIn>
                    <li><g:link controller="main"><i class="icon-user icon-white"></i> <sec:username/></g:link></li>
                    <li class=""></li>
                    <li><g:link controller="logout"><i class="icon-off icon-white"></i> logout</g:link></li>
                </sec:ifLoggedIn>
            </ul>
        </div>
    </div>
</nav>

<div class="container">

    <br />
    <g:layoutBody/>

    <footer>
        <hr />
        <p>&copy; Chelab 2012</p>
    </footer>
</div>

<r:layoutResources/>

</body>
</html>