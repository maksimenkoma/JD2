package by.it_academy.jd2.m_jd2_88_22.chat.view;

import by.it_academy.jd2.m_jd2_88_22.chat.model.Message;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Pageable;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.FactoryStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IMessageStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.StorageMessageHibernate;
import by.it_academy.jd2.m_jd2_88_22.chat.view.api.IMessageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class MessageService implements IMessageService {

    private static final MessageService instance = new MessageService();

    private IMessageStorage storage;

    public MessageService() {

        this.storage = FactoryStorage.getInstance().getMessageStorage();
    }


    @Override
    public void sendMessage(Message message, User userActive) {

        storage.saveMessage(message, userActive);

    }

    @Override
    public void historyMessage(HttpServletRequest req, HttpServletResponse resp, User userActive) throws ServletException, IOException {

        historyMessage(req, resp, userActive, null);
    }


    @Override
    public void historyMessage(HttpServletRequest req, HttpServletResponse resp, User userActive, Pageable pageable) throws ServletException, IOException {

        List<Message> messages = storage.getHistoryMessage(userActive, pageable);


        req.setAttribute("user", userActive.getLogin());

        req.setAttribute("history", messages);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/chats.jsp");
        requestDispatcher.forward(req, resp);


    }

    public static MessageService getInstance() {
        return instance;
    }
}
