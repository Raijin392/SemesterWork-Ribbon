package ru.kpfu.itis.ribbon.servlets.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    private final String[] pageForAuthorized = {"/post", "/profile", "/myPosts", "/mySubs", "/createPost", "/unsub", "/sub", "/authors", "/ServerStorage", "/storage"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);

        boolean isAuthPage = false;
        for (String page : pageForAuthorized) {
            if (req.getRequestURI().contains(req.getContextPath() + page)) {
                isAuthPage = true;
            }
        }

        boolean resStatic;
        if (req.getRequestURI().contains("/js") || req.getRequestURI().contains("/style")) {
            resStatic = true;
        } else {
            resStatic = false;
        }

        boolean isAuth = false;
        if (session != null) {
            isAuth = session.getAttribute("isAuth") != null;
        }

        if (!isAuth && isAuthPage && !resStatic) {
            resp.sendRedirect(req.getContextPath() + "/signin");
        } else if (isAuth && !isAuthPage && !resStatic) {
            resp.sendRedirect(req.getContextPath() + "/profile");
        } else {
            filterChain.doFilter(req, resp);
        }

    }

    @Override
    public void destroy() {
    }

}
