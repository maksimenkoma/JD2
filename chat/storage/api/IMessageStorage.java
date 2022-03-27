package by.it_academy.jd2.m_jd2_88_22.chat.storage.api;

import by.it_academy.jd2.m_jd2_88_22.chat.model.Message;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Pageable;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;

import java.util.List;

public interface IMessageStorage {


    void saveMessage(Message message, User activeUser);

    List<Message> getHistoryMessage(User userActive);

    List<Message> getHistoryMessage(User userActive, Pageable pageable);


}
