package ru.kpfu.itis.ribbon.service;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.kpfu.itis.ribbon.DAO.PostDAO;
import ru.kpfu.itis.ribbon.Encryptors.Encryptor;
import ru.kpfu.itis.ribbon.Exception.DatabaseException;
import ru.kpfu.itis.ribbon.Exception.EncryptorException;
import ru.kpfu.itis.ribbon.Exception.PropertiesException;
import ru.kpfu.itis.ribbon.model.Post;
import ru.kpfu.itis.ribbon.provider.ConnectionProvider;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;
import java.util.List;

public class PostService {

    private final PostDAO postDAO;

    public PostService() throws PropertiesException, DatabaseException {
        ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
        postDAO = new PostDAO(connectionProvider);
    }

    public List<Post> getPageByAuthor(Integer author_id) {
        try {
            return postDAO.getPageByAuthor(author_id);
        } catch (DatabaseException e) {
            return null;
        }
    }

    public List<Post> getPage(Integer userID) {
        try {
            return postDAO.getPage(userID);
        } catch (DatabaseException e) {
            return null;
        }
    }

    public Post getPostByID(Integer postID) {
        try {
            return postDAO.getDetail(postID);
        } catch (DatabaseException e) {
            return null;
        }
    }

    public boolean createPost(HttpServletRequest req, HttpServletResponse resp) {
        ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
        String time = System.currentTimeMillis() + "";
        Integer userID = (Integer) req.getSession().getAttribute("user_id");

        String uriTextContent = null;
        String uriImageContent = null;

        try {
            List<FileItem> multiFiles = fileUpload.parseRequest(req);
            if (multiFiles != null) {
                try {
                    System.out.println(multiFiles.get(0) + " - text");
                    System.out.println(multiFiles.get(1) + " - image");
                    if (multiFiles.get(0) != null) {
                        System.out.println(multiFiles.get(0).getName());
                        uriTextContent =  time + "text" + userID + ".txt";
                        multiFiles.get(0).write(new File("C:\\Users\\Raijin\\ServerStorage\\TextContent\\" + uriTextContent));
                    }
                    if (multiFiles.get(1) != null) {
                        System.out.println(multiFiles.get(1).getName());
                        uriImageContent = time + "image" + userID + ".jpg";
                        multiFiles.get(1).write(new File("C:\\Users\\Raijin\\ServerStorage\\MediaContent\\" + uriImageContent));
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        }

        if (uriImageContent == null && uriTextContent == null) {
            return false;
        } else {
            return postDAO.createPost(userID, uriTextContent, uriImageContent);
        }
    }

}
