package ru.kpfu.itis.ribbon.servlets.servlet;

import ru.kpfu.itis.ribbon.Exception.DatabaseException;
import ru.kpfu.itis.ribbon.Exception.PropertiesException;
import ru.kpfu.itis.ribbon.model.User;
import ru.kpfu.itis.ribbon.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) getServletContext().getAttribute("UserService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = (Integer) req.getSession().getAttribute("user_id");
        User user = userService.getUserByID(userId);
        req.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/WEB-INF/view/user/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        System.out.println(action);
        if (action.equals("logout")) {
            req.getSession().invalidate();
            resp.sendRedirect("/ribbon/signin");
        }
        else if (action.equals("delete")) {
            Integer userId = (Integer) req.getSession().getAttribute("user_id");
            req.getSession().invalidate();
            userService.deleteUser(userId);
            resp.sendRedirect("/ribbon/signup");
        }

    }
}
