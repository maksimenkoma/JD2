package by.it_academy.jd2.m_jd2_88_22.chat.storage.serviceStorage.api;

import by.it_academy.jd2.m_jd2_88_22.chat.model.Message;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;

import java.util.List;

public interface IStorageService {

    User checkUsers(User userRaw);

    void createUsers(User user);

    User checkLoginStorage(String login,String password);

    List<User> getUsers();

    void sendMessageStorageService(Message message);

    List<Message> getHistoryMessages(User activeUser);

    User getActiveUser();

    void createActiveUser(User user);

}



