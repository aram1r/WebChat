package system.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Component
public class ChatBotImpl implements ChatBot{


    private HashMap<String, ArrayList<String>> links;
    private final String chatBot="@bot";
    public ChatBotImpl() {
        links = new HashMap<>();
    }
    public final HashMap<String, String> botCommands = new HashMap<>();

    {
        botCommands.put("help", "Список команд бота \n");
        botCommands.put("link", "Даёт ссылку на статью на Хабре \n");
        botCommands.put("reset", "Скидывает список выданных сайто \n");
    }


    @Override
    public String getAnswer(String request, String login) {
        String command;
        if(request.startsWith(chatBot)) {
            command = request.substring(chatBot.length()).trim();
            if (command.equals("link")) {
                return getLink(login);
            }
            if (command.equals("reset")) {
                return resetLinks(login);
            }
            if (command.equals("help")) {
                return botCommandsPrint();
            }
        }
        return "Вы не ввели команду для бота.";
    }

    private String botCommandsPrint() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = botCommands.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            stringBuilder.append(pair.getKey() + " - " + pair.getValue());
        }
        return stringBuilder.toString();
    }

    //Метод для получения ссылки
    private String getLink(String login) {
        Integer articleNumber = (int) Math.round(Math.random()*300000);
        String site ="https://habr.com/ru/post/" + articleNumber + "/";
        URI uri;
        if (!isLinkSent(Integer.toString(articleNumber), login)) {
            try {
                uri = new URI(site);
                Document doc = Jsoup.connect(uri.toURL().toString()).get();
                Elements elements = doc.getElementsByAttributeValue("content", "article");
                if (!elements.isEmpty()) {
                    elements = doc.getElementsByAttributeValue("content", "Мы знаем много недоделок на сайте… но!");
                    if (elements.isEmpty()) {
                        links.get(login).add(Integer.toString(articleNumber));
                    }
                }
            } catch (URISyntaxException | IOException e) {
                if (e.getMessage().equals("HTTP error fetching URL")) {
                    getLink(login);
                }
            }
        } else {
            getLink(login);
        }
        return site;
    }

    //Очистка списка
    public String resetLinks(String login) {
        if (links.containsKey("login")) {
            links.remove(login);
        }
        return "Список обнулён";
    }

    //Проверяем была ли ссылка отослана
    private boolean isLinkSent(String link, String login) {
            ArrayList<String> linksForCheck = links.get(login);
            if (linksForCheck==null) {
                links.put(login, new ArrayList<>());
                return false;
            }
            if (links.containsKey(login)) {
                for (String linkFromMap : linksForCheck) {
                    if (link.equals(linkFromMap)) {
                        return true;
                    }
                }
            }
            return false;
    }
}
