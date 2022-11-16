package ru.kpfu.itis.ribbon.model;

import ru.kpfu.itis.ribbon.Exception.DatabaseException;
import ru.kpfu.itis.ribbon.Exception.PropertiesException;
import ru.kpfu.itis.ribbon.service.UserService;

import java.io.*;
import java.util.Date;

public class Post {
    private Integer id;
    private Integer author;
    private String textContent;
    private String mediaContent;
    private Date postingTime;

    public Post(Integer id, Integer author, String textContent, String mediaContent, Date postingTime) {
        this.id = id;
        this.author = author;
        this.textContent = textContent;
        this.mediaContent = mediaContent;
        this.postingTime = postingTime;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAuthor() {
        return author;
    }

    public String getTextContent() throws FileNotFoundException {

        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\Users\\Raijin\\ServerStorage\\TextContent\\" + textContent)))) {
            String line;
            while ((line = reader.readLine()) !=null) {
                text.append(line);
            }
        } catch (IOException e) {
            return null;
        }
        return text.toString();
    }

    public String getMediaContent() {
        return mediaContent;
    }

    public Date getPostingTime() {
        return postingTime;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public void setMediaContent(String mediaContent) {
        this.mediaContent = mediaContent;
    }

    public void setPostingTime(Date postingTime) {
        this.postingTime = postingTime;
    }

    public String getAuthorNameByID(Integer id) {
        try {
            return new UserService().getUserByID(id).getUsername();
        } catch (PropertiesException | DatabaseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getImageAuthor() {
        try {
            return new UserService().getUserByID(author).getPhotoProfile();
        } catch (PropertiesException | DatabaseException e) {
            throw new RuntimeException(e);
        }
    }
}
