package by.it_academy.jd2.m_jd2_88_22.chat.endpoints;

import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import by.it_academy.jd2.m_jd2_88_22.chat.view.MessageService;
import by.it_academy.jd2.m_jd2_88_22.chat.view.api.IMessageService;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "MessageServlet", urlPatterns = "/message")
public class MessageServlet extends HttpServlet {


    private IMessageService messageService;


    public MessageServlet() {


        this.messageService = MessageService.getInstance();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/message.jsp");
        requestDispatcher.forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String loginSender = req.getParameter("sender");
        String message = req.getParameter("message");

        HttpSession session = req.getSession();
        User userActive = (User) session.getAttribute("user");

        try {
            messageService.sendMessage(new Message(loginSender, message, LocalDateTime.now()), userActive);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/message.jsp");
            requestDispatcher.forward(req, resp);

        } catch (Exception e) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/errorSend.jsp");
            requestDispatcher.forward(req, resp);
        }

    }
}
