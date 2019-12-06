<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>

<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<#include "../main-template.ftl"/>

<#macro content>
    <@sf.form action="/users/new" method="post" modelAttribute="user">
        <div>
            <@sf.label path="login">Login</@sf.label>
            <@sf.input path="login"/>
            <@sf.errors path="login"/>
        </div>
        <div>
            <@sf.label path="password">Password</@sf.label>
            <@sf.input path="password"/>
            <@sf.errors path="password"/>
        </div>
        <div>
            <@sf.label path="name">Name</@sf.label>
            <@sf.input path="name"/>
            <@sf.errors path="name"/>
        </div>
        <div>
            <@sf.label path="surname">Surname</@sf.label>
            <@sf.input path="surname"/>
            <@sf.errors path="surname"/>
        </div>
        <input type="submit">
    </@sf.form>
</#macro>

<@main title="Sign Up"/>
