package by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.api;

import by.it_academy.jd2.m_jd2_88_22.chat.model.Message;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Pageable;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import by.it_academy.jd2.m_jd2_88_22.chat.model.hibernate.MessageHibernate;
import by.it_academy.jd2.m_jd2_88_22.chat.model.hibernate.UserHibernate;

import java.util.List;

public interface IStorageMessageHibernate {

    void saveMessageHibernate(Message message,User activeUser);

    List<Message> getHistoryMessageHibernate(User activeUser);

    List<Message> getHistoryMessageHibernate(User activeUser, Pageable pageable);

}
