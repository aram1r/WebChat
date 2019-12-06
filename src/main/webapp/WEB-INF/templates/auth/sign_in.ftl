<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<#include "../main-template.ftl"/>

<#macro content>
    <form action="/login/process" modelAttribute="userFTL" method="post" >
        <div id="username-page">
            Login : <input name="login" type="login" id="name"/>
        </div>
        <div>
            Password : <input name ="password" type="password"/>
        </div>
        <input type="submit" class="accent username-submit">
    </form>
    <button id="sign_up">Регистрация</button>
    <script type="text/javascript">
        document.getElementById("sign_up").onclick = function () {
            location.href = "/users/new";
        };
    </script>
    <script src="../../../resources/static/js/main.js"></script>
    <#if error??>
        <p>Bad credentials</p>
    </#if>
</#macro>


<@main title="login"/>
