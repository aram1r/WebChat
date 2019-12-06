<html>
<head>
    <title>Users</title>
</head>
    <body>
    <#if users?has_content>
        <ul>
            <#list users as user>
                <li>${user.login} ${user.password} ${user.name} ${user.surname}</li>
            </#list>
        </ul>
    <#else>
        <p>No users yet!</p>
    </#if>
    <a href="/logout">Logout</a>
    </body>
</html>