package ru.kpfu.itis.ribbon.servlets.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/storage/*")
public class StorageServlet extends HttpServlet {

    // text/plain
    // C:\Users\Raijin\Desktop\Image

    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        InputStream inputStream = new FileInputStream("C:\\Users\\Raijin\\apache-tomcat-9.0.65\\ServerStorage\\" + "1668535754738image1.jpg");
//        byte[] imageBytes = inputStream.readAllBytes();
//        resp.setContentType(getServletContext().getMimeType("image/jpeg"));
//        resp.getOutputStream().write(imageBytes);

        String fileName = req.getPathInfo().substring(1);

        if (fileName.contains("profile")) {
            InputStream inputStream = new FileInputStream("C:\\Users\\Raijin\\ServerStorage\\ProfileImage\\" + fileName);
            byte[] imageBytes = inputStream.readAllBytes();
            resp.setContentType(getServletContext().getMimeType("image/jpeg"));
            resp.getOutputStream().write(imageBytes);
        }
        if (fileName.contains("image")) {
            InputStream inputStream = new FileInputStream("C:\\Users\\Raijin\\ServerStorage\\MediaContent\\" + fileName);
            byte[] imageBytes = inputStream.readAllBytes();
            resp.setContentType(getServletContext().getMimeType("image/jpeg"));
            resp.getOutputStream().write(imageBytes);
        }

    }
}