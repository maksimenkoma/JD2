package by.it_academy.jd2.m_jd2_88_22.chat.storage.hibernate.api;

import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import by.it_academy.jd2.m_jd2_88_22.chat.model.hibernate.UserHibernate;

import java.util.List;

public interface IStorageUserHibernate {

    void saveUserHibernate(User user);

    List<User> pullUserHibernate();

    List<User> pullUserHibernate(User user);

    User pullUserHibernate(String login,String password);

}
