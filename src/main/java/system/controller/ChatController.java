package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import system.dao.impl.HibernateUserDAO;
import system.model.ChatBot;
import system.model.ChatMessage;

@Controller
public class ChatController {

    @Autowired
    private HibernateUserDAO hibernateUserDAO;

    @Autowired
    private ChatBot chatBot;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        chatMessage.setSender(hibernateUserDAO.getNameSurname(chatMessage.getSender()));
        return chatMessage;
    }

    @Order(1)
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", hibernateUserDAO.getNameSurname(chatMessage.getSender()));
        chatMessage.setSender(hibernateUserDAO.getNameSurname(chatMessage.getSender()));
        return chatMessage;
    }

    @Order(2)
    @MessageMapping("/chat.bot")
    @SendTo("/topic/public")
    public ChatMessage sendLink(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
//        chatMessage.setContent("link");
//        chatMessage.setSender("bot");
        ChatMessage respondChatMessage = new ChatMessage();
        respondChatMessage.setSender("bot");
        respondChatMessage.setContent(chatBot.getAnswer(chatMessage.getContent(),chatMessage.getSender()));
        respondChatMessage.setType(ChatMessage.MessageType.CHAT);
        return respondChatMessage;
    };

}
