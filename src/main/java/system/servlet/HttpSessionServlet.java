package system.servlet;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import system.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/servlet")
public class HttpSessionServlet extends HttpServlet {

    private static final String LOGIN = "login";



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession httpSession = req.getSession();

        String login = (String) httpSession.getAttribute("userJPL");

        PrintWriter out = resp.getWriter();
        try {
            // если объект ранее не установлен
            if(login == null) {
                // устанавливаем объект с ключом name
                httpSession.setAttribute("name", "Tom Soyer");
                out.println("Session data are set");
            }
            else {
                out.println("Name: " + login);
                // удаляем объект с ключом name
                httpSession.removeAttribute("name");
            }
        }
        finally {
            out.close();
        }
    }
}
