package by.it_academy.jd2.m_jd2_88_22.chat.view.api;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Message;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Pageable;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public interface IMessageService {


    void sendMessage(Message message, User userActive);

    void historyMessage(HttpServletRequest req, HttpServletResponse resp,User userActive) throws ServletException, IOException;

    void historyMessage(HttpServletRequest req, HttpServletResponse resp,User userActive, Pageable pageable) throws ServletException, IOException;



}
