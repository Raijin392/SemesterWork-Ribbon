package ru.kpfu.itis.ribbon.servlets.servlet;

import ru.kpfu.itis.ribbon.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mySubs")
public class MySubscriptionsServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) getServletContext().getAttribute("UserService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userID = (Integer) req.getSession().getAttribute("user_id");
        req.setAttribute("authors", userService.getSubscriptionsByUserID(userID));
        getServletContext().getRequestDispatcher("/WEB-INF/view/user/userSubscriptions.jsp").forward(req, resp);
    }

}
