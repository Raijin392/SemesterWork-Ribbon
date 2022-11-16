package ru.kpfu.itis.ribbon.servlets.servlet;

import ru.kpfu.itis.ribbon.service.SubscriptionService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sub")
public class SubscribeServlet extends HttpServlet {

    SubscriptionService subscriptionService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        subscriptionService = (SubscriptionService) getServletContext().getAttribute("SubscriptionService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer subscriberID = (Integer) req.getSession().getAttribute("user_id");
        Integer authorID = Integer.valueOf(req.getParameter("author_id"));
        subscriptionService.offerSubscription(authorID, subscriberID);
        resp.sendRedirect("/ribbon/authors");
    }

}
