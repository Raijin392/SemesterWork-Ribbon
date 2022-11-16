package ru.kpfu.itis.ribbon.servlets;

import ru.kpfu.itis.ribbon.Exception.PropertiesException;
import ru.kpfu.itis.ribbon.Exception.DatabaseException;
import ru.kpfu.itis.ribbon.service.PostService;
import ru.kpfu.itis.ribbon.service.SubscriptionService;
import ru.kpfu.itis.ribbon.service.UserService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {

            sce.getServletContext().setAttribute("PostService", new PostService());
            sce.getServletContext().setAttribute("UserService", new UserService());
            sce.getServletContext().setAttribute("SubscriptionService", new SubscriptionService());

        } catch (DatabaseException | PropertiesException e) {
            throw new RuntimeException(e);
        }
    }

}
