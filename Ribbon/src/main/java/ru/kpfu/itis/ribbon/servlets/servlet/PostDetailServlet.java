package ru.kpfu.itis.ribbon.servlets.servlet;

import ru.kpfu.itis.ribbon.DAO.PostDAO;
import ru.kpfu.itis.ribbon.Exception.DatabaseException;
import ru.kpfu.itis.ribbon.service.PostService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/post/*")
public class PostDetailServlet extends HttpServlet {

    private PostService postService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        postService = (PostService) getServletContext().getAttribute("PostService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURL().reverse().toString();
        String id = uri.split("/")[0];
        if (id.equals("")) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            getServletContext().getRequestDispatcher("/WEB-INF/view/errors/notFound.jsp").forward(req, resp);
        }
        req.setAttribute("postDetail", postService.getPostByID(Integer.parseInt(id)));
        getServletContext().getRequestDispatcher("/WEB-INF/view/posts/postDetail.jsp").forward(req, resp);
    }
}
