package by.it_academy.jd2.m_jd2_88_22.chat.endpoints;

import by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.StorageMessageHibernate;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.StorageUserHibernate;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.api.IStorageMessageHibernate;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.api.IStorageUserHibernate;
import by.it_academy.jd2.m_jd2_88_22.chat.view.MessageService;
import by.it_academy.jd2.m_jd2_88_22.chat.view.UserService;
import by.it_academy.jd2.m_jd2_88_22.chat.view.api.IMessageService;
import by.it_academy.jd2.m_jd2_88_22.chat.view.api.IUserService;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "MessageServlet", urlPatterns = "/message")
public class MessageServlet extends HttpServlet {


    private IUserService userService;
    private IMessageService messageService;
    private IStorageMessageHibernate storageMessageHibernate;
    private IStorageUserHibernate storageUserHibernate;

    public MessageServlet() {

        this.userService = UserService.getInstance();
        this.messageService = MessageService.getInstance();
        this.storageMessageHibernate = StorageMessageHibernate.getInstance();
        this.storageUserHibernate = StorageUserHibernate.getInstance();
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

        try {
            messageService.sendMessage(new Message(loginSender,message,LocalDateTime.now()));
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/message.jsp");
            requestDispatcher.forward(req, resp);

        } catch (Exception e) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/errorSend.jsp");
            requestDispatcher.forward(req, resp);
        }

    }
}
