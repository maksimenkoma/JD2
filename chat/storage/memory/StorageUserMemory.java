package by.it_academy.jd2.m_jd2_88_22.chat.storage.memory;

import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IUserStorage;

import java.util.ArrayList;
import java.util.List;

public class StorageUserMemory implements IUserStorage {

    private static final StorageUserMemory instance = new StorageUserMemory();


    private List<User> users = new ArrayList<>();

    @Override
    public User checkUser(String login, String password) {

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
    public void saveUser(User user) {
        this.users.add(user);
    }


    @Override
    public User getUser(User userRaw) {

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


    public List<User> getListUser(){

        return this.users;
    }


    public static StorageUserMemory getInstance() {
        return instance;
    }
}
