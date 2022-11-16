package ru.kpfu.itis.ribbon.servlets.servlet;

import ru.kpfu.itis.ribbon.Exception.EncryptorException;
import ru.kpfu.itis.ribbon.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Отвечает по запросу / {слэш}
@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

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
        if (servletContext != null) {
            req.setAttribute("errorUncorrectUsername", servletContext.getAttribute("errorUncorrectUsername"));
            req.setAttribute("errorUncorrectEmail", servletContext.getAttribute("errorUncorrectEmail"));
            req.setAttribute("errorUncorrectPassword", servletContext.getAttribute("errorUncorrectPassword"));
        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/security/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            boolean userCreated = userService.createUser(req, resp);

            if (userCreated) {
                resp.sendRedirect("/ribbon/profile");
            } else {
                resp.sendRedirect("/ribbon/signup");
            }

        } catch (EncryptorException e) {
            throw new RuntimeException(e);
        }
    }
}