package ru.kpfu.itis.ribbon.servlets.servlet;

import ru.kpfu.itis.ribbon.service.PostService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/myPosts")
public class MyPostsServlet extends HttpServlet {

    private PostService postService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        postService = (PostService) getServletContext().getAttribute("PostService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Integer userID = (Integer) req.getSession().getAttribute("user_id");
        req.setAttribute("posts", postService.getPageByAuthor(userID));
        getServletContext().getRequestDispatcher("/WEB-INF/view/user/userPosts.jsp").forward(req, resp);
    }

}
