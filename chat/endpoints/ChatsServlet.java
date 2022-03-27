package by.it_academy.jd2.m_jd2_88_22.chat.endpoints;

import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import by.it_academy.jd2.m_jd2_88_22.chat.view.MessageService;
import by.it_academy.jd2.m_jd2_88_22.chat.view.api.IMessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@WebServlet(name = "ChatsServlet", urlPatterns = "/chats")
public class ChatsServlet extends HttpServlet {

 private IMessageService messageService;



    public ChatsServlet() {

        this.messageService = MessageService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User userActive = (User) session.getAttribute("user");

       messageService.historyMessage(req, resp,userActive);

    }
}