package ru.kpfu.itis.ribbon.DAO;

import ru.kpfu.itis.ribbon.provider.ConnectionProvider;
import ru.kpfu.itis.ribbon.Exception.DatabaseException;
import ru.kpfu.itis.ribbon.model.Post;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {

    private final ConnectionProvider connectionProvider;

    public PostDAO(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public List<Post> getPage(Integer userID) throws DatabaseException {
        try {
            PreparedStatement statement = this.connectionProvider
                    .getConnection()
                    .prepareStatement("SELECT p.* FROM post as p inner join subscription as s on p.fk_author_id = s.fk_author_id where s.fk_subscriber_id = ? ORDER BY p.posting_time;");
            statement.setInt(1, userID);
            ResultSet data = statement.executeQuery();
            List<Post> posts = new ArrayList<>();
            while (data.next()) {
                Post post = new Post(
                        data.getInt("id"),
                        data.getInt("fk_author_id"),
                        data.getString("text_content"),
                        data.getString("media_content"),
                        data.getDate("posting_time")
                );
                posts.add(post);
            }
            return posts;
        } catch (SQLException e) {
            throw new DatabaseException("Can't get post list from DB", e);
        }
    }

    public Post getDetail(Integer id) throws DatabaseException {
        try {
            PreparedStatement statement = this.connectionProvider
                    .getConnection()
                    .prepareStatement("SELECT * FROM post WHERE id = ?;");
            statement.setInt(1, id);
            ResultSet data = statement.executeQuery();
            data.next();
            return new Post(
                    data.getInt("id"),
                    data.getInt("fk_author_id"),
                    data.getString("text_content"),
                    data.getString("media_content"),
                    data.getDate("posting_time")
            );
        } catch (SQLException e) {
            throw new DatabaseException("Can't get post from DB", e);
        }
    }

    public List<Post> getPageByAuthor(Integer authorID) throws DatabaseException {
        try {
            PreparedStatement statement = this.connectionProvider
                    .getConnection()
                    .prepareStatement("SELECT * FROM post WHERE fk_author_id = ? ORDER BY posting_time;");
            statement.setInt(1, authorID);
            ResultSet data = statement.executeQuery();
            List<Post> posts = new ArrayList<>();
            while (data.next()) {
                Post post = new Post(
                        data.getInt("id"),
                        data.getInt("fk_author_id"),
                        data.getString("text_content"),
                        data.getString("media_content"),
                        data.getDate("posting_time")
                );
                posts.add(post);
            }
            return posts;
        } catch (SQLException e) {
            throw new DatabaseException("Can't get post list from DB", e);
        }
    }

    public boolean createPost(Integer authorID, String textContent, String mediaContent) {
        try {
            PreparedStatement statement = this.connectionProvider
                    .getConnection()
                    .prepareStatement("INSERT INTO post(fk_author_id, text_content, media_content, posting_time) VALUES (?, ?, ?, NOW())");
            statement.setInt(1, authorID);
            statement.setString(2, textContent);
            statement.setString(3, mediaContent);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

}
