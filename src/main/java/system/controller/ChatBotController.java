package system.controller;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import system.model.ChatMessage;

@Controller
public class ChatBotController {

    @MessageMapping("/message/bot")
    @SendTo("/chat/messages")
    public ChatMessage getMessages(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }
}
