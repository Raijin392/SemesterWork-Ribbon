package ru.kpfu.itis.ribbon.servlets.servlet;

import ru.kpfu.itis.ribbon.Exception.EncryptorException;
import ru.kpfu.itis.ribbon.model.User;
import ru.kpfu.itis.ribbon.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) getServletContext().getAttribute("UserService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("name", "Ribbon");
        ServletContext servletContext = req.getServletContext();
        if(servletContext.getAttribute("errorUncorrectEmailOrPassword") != null) {
            req.setAttribute("errorUncorrectEmailOrPassword", servletContext.getAttribute("errorUncorrectEmailOrPassword"));
        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/security/signin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ServletContext servletContext = req.getServletContext();
        try {
            User user = userService.getUserByEmailAndPassword(req, resp);
            if (user != null) {
                session.setAttribute("isAuth", true);
                session.setAttribute("user_id", user.getId());
                servletContext.removeAttribute("error");
                getServletContext().getRequestDispatcher("/WEB-INF/view/user/profile.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("/ribbon/signin");
            }
        } catch (EncryptorException e) {
            throw new RuntimeException(e);
        }


    }
}
