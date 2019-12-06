<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<#include "main-template.ftl"/>

<#macro content>
    <noscript>
        <h2>Sorry! Your browser doesn't support Javascript</h2>
    </noscript>
    <ul class="list-group list-group-horizontal">
        <li class="list-group-item">
            <span>Пользователи онлайн</span>
        </li>
        <li class="list-group-item">
<#--            <div id="username-page">-->
<#--                <div class="username-page-container">-->
<#--                    <h1 class="title">Type your username</h1>-->
<#--                    <form id="usernameForm" name="usernameForm">-->
<#--                        <div class="form-group">-->
<#--                            <input type="text" id="name" placeholder="Username" autocomplete="off" class="form-control" />-->
<#--                        </div>-->
<#--                        <div class="form-group">-->
<#--                            <button type="submit" class="accent username-submit">Start Chatting</button>-->
<#--                        </div>-->
<#--                    </form>-->
<#--                </div>-->
<#--            </div>-->
            <div id="chat-page" class="hidden">
                <div class="chat-container">
                    <div class="chat-header">
                        <h2>Spring WebSocket Chat Demo</h2>
                    </div>
                    <div class="connecting">
                        Connecting...
                    </div>
                    <ul id="messageArea">
                    </ul>
                    <form id="messageForm" name="messageForm">
                        <div class="form-group">
                            <div class="input-group clearfix">
                                <input type="text" id="message" placeholder="Type a message..." autocomplete="off" class="form-control"/>
                                <button type="submit" class="primary">Send</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </li>
    </ul>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="../../resources/static/js/main.js"></script>
    <script>window.addEventListener('load', connect)</script>
</#macro>

<@main title="chat"/>