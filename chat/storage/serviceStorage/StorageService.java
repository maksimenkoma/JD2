package by.it_academy.jd2.m_jd2_88_22.chat.storage.serviceStorage;

import by.it_academy.jd2.m_jd2_88_22.chat.model.Message;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.serviceStorage.api.IStorageService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageService implements IStorageService {

    private static final StorageService instance = new StorageService();

    private List<User> users = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();
    private Map<String, List<Message>> historyMessages = new HashMap<>();
    private User activeUser;


    public StorageService() {


    }


    @Override
    public User checkUsers(User userRaw) {


        User checkUser = userRaw;

        for (User user : users) {

            if (user.getLogin().equals(userRaw.getLogin()) ||
                (userRaw.getLogin().equals("") || userRaw.getPassword().equals("") || userRaw.getFirstName().equals("")
                 || userRaw.getLastName().equals("") || userRaw.getDateBirth().equals(""))) {

                checkUser = null;

            }
        }
        return checkUser;
    }


    @Override
    public void createUsers(User user) {
        this.users.add(user);

    }

    @Override
    public User checkLoginStorage(String login, String password) {

        User checkLogin = null;

        for (User user : users) {

            if (user.getLogin().equals(login)) {

                if (user.getPassword().equals(password)) {
                    checkLogin = user;


                }
            }
        }

        return checkLogin;
    }


    @Override
    public List<User> getUsers() {
        return users;
    }


    @Override
    public void sendMessageStorageService(Message message) {

        for (User user : users) {
            if (user.getLogin().equals(message.getRecipient()) && !activeUser.getLogin().equals(message.getRecipient())) {

                if (historyMessages.containsKey(message.getRecipient())) {

                    historyMessages.get(message.getRecipient()).add(new Message(activeUser.getLogin(), message.getMessage(), LocalDateTime.now()));

                } else {
                    List<Message> message1 = new ArrayList<>();

                    message1.add(new Message(activeUser.getLogin(), message.getMessage(), LocalDateTime.now()));

                    historyMessages.put(message.getRecipient(), message1);

                }


            }

        }
    }

    @Override
    public List<Message> getHistoryMessages(User activeUser) {

        List<Message> messages = new ArrayList<>();
        if (historyMessages.containsKey(activeUser.getLogin())) {

            messages = historyMessages.get(activeUser.getLogin());

        }
        return messages;
    }


    @Override
    public User getActiveUser() {
        return this.activeUser;
    }


    @Override
    public void createActiveUser(User user) {
        this.activeUser = user;
    }




    public static StorageService getInstance() {
        return instance;
    }

}



