<form action="${postUrl}" method="post" id="frmLogin" class="form-horizontal">
    <fieldset>
        <legend><g:message code="springSecurity.login.header"/></legend>
        <div class="control-group">
            <label class="control-label" for="username">
                <g:message code="springSecurity.login.username.label"/>
            </label>
            <div class="controls">
                <input type='text' class='input-medium' name='j_username' id='username'/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="password">
                <g:message code="springSecurity.login.password.label"/>
            </label>
            <div class="controls">
                <input type='password' class='input-medium' name='j_password' id='password'/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="remember_me">
                <g:message code="springSecurity.login.remember.me.label"/>
            </label>
            <div class="controls">
                <input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
            </div>
        </div>
        <div class="form-actions">
            <input type='submit' class="btn btn-primary" id="submit" value='${message(code: "springSecurity.login.button")}'/>
        </div>
    </fieldset>
</form>