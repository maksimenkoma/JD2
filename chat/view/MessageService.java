package by.it_academy.jd2.m_jd2_88_22.chat.view;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Message;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Pageable;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.FactoryStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IFactoryStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.view.api.IMessageService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class MessageService implements IMessageService {

    private static final MessageService instance = new MessageService();

    private IFactoryStorage factoryStorage;

    public MessageService() {

        this.factoryStorage = FactoryStorage.getInstance();
    }


    @Override
    public void sendMessage(Message message) {


        factoryStorage.getHibernateStorage().getStorageMessageHibernate().saveMessageHibernate(message,
                factoryStorage.getStorageFactory().getStorageService().getActiveUser());

    }

    @Override
    public void historyMessage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        historyMessage(req, resp, null);
    }


    @Override
    public void historyMessage(HttpServletRequest req, HttpServletResponse resp, Pageable pageable) throws ServletException, IOException {

        List<Message> messages =

                factoryStorage.getHibernateStorage().getStorageMessageHibernate().
                        getHistoryMessageHibernate(factoryStorage.getStorageFactory().getStorageService().getActiveUser(), pageable);


        req.setAttribute("user", factoryStorage.getStorageFactory().getStorageService().getActiveUser().getLogin());

        req.setAttribute("history", messages);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/chats.jsp");
        requestDispatcher.forward(req, resp);


    }

    public static MessageService getInstance() {
        return instance;
    }
}
