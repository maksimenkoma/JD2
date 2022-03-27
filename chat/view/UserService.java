package by.it_academy.jd2.m_jd2_88_22.chat.view;

import by.it_academy.jd2.m_jd2_88_22.chat.model.Audit;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.FactoryStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IAuditStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IUserStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.view.api.IUserService;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;


public class UserService implements IUserService {

    private static final UserService instance = new UserService();

    private IUserStorage storage;
    private IAuditStorage auditStorage;

    public UserService() {

        this.storage = FactoryStorage.getInstance().getUserStorage();
        this.auditStorage = FactoryStorage.getInstance().getAuditStorage();
    }

    @Override

    public boolean checkUsers(User userRaw) {

        boolean log = true;

        if (storage.getUser(userRaw) == null) {
            log = false;

        }
        return log;
    }

    @Override
    public User checkLogin(String login, String password) {

        return storage.checkUser(login, password);
    }


    @Override
    public User createUser(User userRaw) {

        User user = new User();

        if (userRaw.getLogin() != null && userRaw.getPassword() != null && userRaw.getFirstName() != null &&
            userRaw.getLastName() != null && userRaw.getDateBirth() != null) {
            if (!userRaw.getLogin().equals("") && !userRaw.getPassword().equals("") &&
                !userRaw.getFirstName().equals("") && !userRaw.getLastName().equals("") && !userRaw.getDateBirth().equals("")) {
                if (userRaw.getMiddleName() != null) {
                    user = new User(userRaw.getLogin(), userRaw.getPassword(), userRaw.getFirstName(),
                            userRaw.getLastName(), userRaw.getMiddleName(),
                            userRaw.getDateBirth());
                }
                if (userRaw.getMiddleName() == null) {
                    user = new User(userRaw.getLogin(), userRaw.getPassword(), userRaw.getFirstName(),
                            userRaw.getLastName(), userRaw.getDateBirth());
                }
            }
        }
        storage.saveUser(user);
     auditStorage.saveAudit(new Audit("Регистрация", user, LocalDateTime.now()));


        return user;
    }


    @Override
    public void createSession(User userActive, HttpServletRequest req) {

        HttpSession session = req.getSession();

        if (userActive.getLogin() == null || userActive.getPassword() == null) {

            userActive = (User) session.getAttribute("user");

            if (userActive.getLogin() == null || userActive.getPassword() == null) {

                throw new IllegalArgumentException("Не передан обязательный параметр");
            }


        } else session.setAttribute("user", userActive);

    }


    public static UserService getInstance() {
        return instance;
    }

}


