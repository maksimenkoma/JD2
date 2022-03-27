package by.it_academy.jd2.m_jd2_88_22.chat.view.api;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import javax.servlet.http.HttpServletRequest;


public interface IUserService {


    boolean checkUsers(User userRaw);

    User createUser(User userRaw);

    User checkLogin(String login,String password);

    void createSession(User user, HttpServletRequest req);



}
