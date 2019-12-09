package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import system.dao.impl.HibernateUserDAO;
import system.model.ChatBot;
import system.model.ChatMessage;
import system.model.User;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    @Qualifier("sessionRegistry")
    private SessionRegistry sessionRegistry;

    private static StringBuilder usersOnline;

    public String listLoggedInUsers() {
        final List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        usersOnline = new StringBuilder();
        for (final Object principal : allPrincipals) {
            if (principal instanceof User) {
                final User user = (User) principal;

                List<SessionInformation> activeUserSessions =
                        sessionRegistry.getAllSessions(principal,
                                /* includeExpiredSessions */ false); // Should not return null;

                if (!activeUserSessions.isEmpty()) {
                    // Do something with user
                    System.out.printf(user.getLogin());
                    usersOnline.append(user.getName()).append(" ").append(user.getSurname()).append("\n");
                }
            }
        }
        return usersOnline.toString();



    }



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
        chatMessage.setContent(listLoggedInUsers());
        return chatMessage;
    }

    @MessageMapping("/chat/addUser")
    @SendTo("/topic/onlineList")
    public ChatMessage sendUsersOnline() {
        ChatMessage onlineList = new ChatMessage();
        onlineList.setSender("server");
        onlineList.setContent(listLoggedInUsers());
        return onlineList;
    }

    @Order(2)
    @MessageMapping("/chat.bot")
    @SendTo("/topic/public")
    public ChatMessage sendLink(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        ChatMessage respondChatMessage = new ChatMessage();
        respondChatMessage.setSender("bot");
        respondChatMessage.setContent(chatBot.getAnswer(chatMessage.getContent(),chatMessage.getSender()));
        respondChatMessage.setType(ChatMessage.MessageType.CHAT);
        return respondChatMessage;
    };

}
