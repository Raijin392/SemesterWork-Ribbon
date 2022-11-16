package ru.kpfu.itis.ribbon.servlets.servlet;

import ru.kpfu.itis.ribbon.service.PostService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createPost")
public class CreatePostServlet extends HttpServlet {
    private PostService postService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        postService = (PostService) getServletContext().getAttribute("PostService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/posts/postCreate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        postService.createPost(req, resp);
        resp.sendRedirect("/ribbon/myPosts");
    }
}
