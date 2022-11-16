package ru.kpfu.itis.ribbon.servlets.servlet;

import ru.kpfu.itis.ribbon.DAO.PostDAO;
import ru.kpfu.itis.ribbon.Exception.DatabaseException;
import ru.kpfu.itis.ribbon.model.User;
import ru.kpfu.itis.ribbon.service.PostService;
import ru.kpfu.itis.ribbon.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/post")
public class PostListServlet extends HttpServlet {

    private PostService postService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        postService = (PostService) getServletContext().getAttribute("PostService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userID = (Integer) req.getSession().getAttribute("user_id");
        req.setAttribute("posts", postService.getPage(userID));
        getServletContext().getRequestDispatcher("/WEB-INF/view/posts/postsList.jsp").forward(req, resp);
    }

}