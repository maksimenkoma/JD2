package by.it_academy.jd2.m_jd2_88_22.chat.endpoints;

import by.it_academy.jd2.m_jd2_88_22.chat.view.UserService;
import by.it_academy.jd2.m_jd2_88_22.chat.view.api.IUserService;
import by.it_academy.jd2.m_jd2_88_22.chat.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "SignUpServlet", urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {

    private IUserService userService;
    private static final String LOGIN = "login";
    private static final String PASSWORD = "pass";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String MIDDLE_NAME = "middleName";
    private static final String DATE = "date";


    public SignUpServlet() {

        this.userService = UserService.getInstance();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/signUp.jsp");
        requestDispatcher.forward(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        String firstName = req.getParameter(FIRST_NAME);
        String lastName = req.getParameter(LAST_NAME);
        String middleName = req.getParameter(MIDDLE_NAME);
        String date = req.getParameter(DATE);

        User userRaw = User.Builder.builder().setLogin(login).setPassword(password).setFirstName(firstName).
                setLastName(lastName).setMiddleName(middleName).
                setDateBirth(LocalDate.parse(date)).build();

        if (userService.checkUsers(userRaw)) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/errorReg.jsp");
            requestDispatcher.forward(req, resp);

        } else userService.createUser(userRaw);



        resp.sendRedirect(req.getContextPath() + "/signIn");

    }
}