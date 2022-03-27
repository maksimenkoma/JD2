package by.it_academy.jd2.m_jd2_88_22.chat.storage.api;

import by.it_academy.jd2.m_jd2_88_22.chat.model.User;

import java.util.List;

public interface IUserStorage {

    User checkUser(String login,String password);

    void saveUser(User user);

    User getUser(User user);

}
