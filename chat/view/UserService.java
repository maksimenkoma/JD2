package by.it_academy.jd2.m_jd2_88_22.chat.view;
import by.it_academy.jd2.m_jd2_88_22.chat.model.Audit;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.FactoryStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.storage.api.IFactoryStorage;
import by.it_academy.jd2.m_jd2_88_22.chat.view.api.IUserService;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;



public class UserService implements IUserService {

    private static final UserService instance = new UserService();

    private IFactoryStorage factoryStorage;

    public UserService() {

        this.factoryStorage = FactoryStorage.getInstance();


    }

    @Override

    public boolean checkUsers(User userRaw) {

        boolean log = true;

        if (factoryStorage.getHibernateStorage().getStorageUserHibernate().pullUserHibernate(userRaw) == null) {
            log = false;

        }
        return log;
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

        factoryStorage.getHibernateStorage().getStorageUserHibernate().saveUserHibernate(user);
        factoryStorage.getHibernateStorage().getStorageAuditHibernate().saveAuditHibernate(new Audit("Регистрация", user.getLogin(), LocalDateTime.now()));

        return user;
    }


    public boolean checkLogin(String login, String password) {

        boolean log = false;

        User user =
                factoryStorage.getHibernateStorage().getStorageUserHibernate().pullUserHibernate(login, password);


        if (user.getLogin().equals(login)) {

            if (user.getPassword().equals(password)) {
                factoryStorage.getStorageFactory().getStorageService().createActiveUser(user);
                log = true;
            }
        }

        return log;
    }


    @Override
    public void createSession(User activeUser, HttpServletRequest req) {

        HttpSession session = req.getSession();

        if (activeUser.getLogin() == null || activeUser.getPassword() == null) {

            activeUser = (User) session.getAttribute("user");

            if (activeUser.getLogin() == null || activeUser.getPassword() == null) {

                throw new IllegalArgumentException("Не передан обязательный параметр");
            }


        } else session.setAttribute("user", activeUser);

    }

    @Override
    public User getActiveUser() {

        return factoryStorage.getStorageFactory().getStorageService().getActiveUser();

    }

    public static UserService getInstance() {
        return instance;
    }

}


