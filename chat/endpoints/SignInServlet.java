package by.it_academy.jd2.m_jd2_88_22.chat.endpoints;

import by.it_academy.jd2.m_jd2_88_22.chat.model.User;
import by.it_academy.jd2.m_jd2_88_22.chat.view.UserService;
import by.it_academy.jd2.m_jd2_88_22.chat.view.api.IUserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "SignInServlet", urlPatterns = "/signIn")
public class SignInServlet extends HttpServlet {


    private IUserService userService;
    private static final String LOGIN = "login";
    private static final String PASSWORD = "pass";

    public SignInServlet() {

        this.userService = UserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/signIn.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);


       User userActive = userService.checkLogin(login, password);

        if (userActive==null) {

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/errorLog.jsp");
            requestDispatcher.forward(req, resp);

        }

        userService.createSession(userActive,req);

        resp.sendRedirect(req.getContextPath() + "/message");

    }
}
