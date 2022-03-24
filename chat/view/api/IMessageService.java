package by.it_academy.jd2.m_jd2_88_22.chat.view.api;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Message;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Pageable;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public interface IMessageService {


    void sendMessage(Message message);

    void historyMessage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    void historyMessage(HttpServletRequest req, HttpServletResponse resp, Pageable pageable) throws ServletException, IOException;



}
