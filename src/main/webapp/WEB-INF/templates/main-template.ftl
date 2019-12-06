<#macro main title>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width = device-width, initial-scale=1">
        <title>${title}</title>
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
        <title>Spring Boot WebSocket Chat Application | CalliCoder</title>

        <link type="text/css" href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <link type="text/css" href="../../resources/static/css/style.css" rel="stylesheet">
        <script src="/webjars/jquery/3.1.0/jquery.min.js"></script>
        <script src="/webjars/sockjs-client/1.0.2/sockjs.min.js"></script>
        <script src="/webjars/stomp-websocket/2.3.3-1/stomp.min.js"></script>
        <base href="/">
    </head>

    <body>
        <@content/>
    </body>
</#macro>