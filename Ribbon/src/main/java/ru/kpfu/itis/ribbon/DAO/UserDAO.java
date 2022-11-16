package ru.kpfu.itis.ribbon.DAO;

import ru.kpfu.itis.ribbon.Exception.DatabaseException;
import ru.kpfu.itis.ribbon.provider.ConnectionProvider;
import ru.kpfu.itis.ribbon.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    final private ConnectionProvider connectionProvider;

    public UserDAO(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public User getUserByEmailAndPassword(String email, String password) {
        try {
            PreparedStatement statement = this.connectionProvider
                    .getConnection()
                    .prepareStatement("SELECT * FROM r_user WHERE email = ? and user_password = ?;");
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet data = statement.executeQuery();
            data.next();
            return new User(
                    data.getInt("id"),
                    data.getString("username"),
                    data.getString("email"),
                    data.getString("photo_profile"),
                    data.getString("user_password")
            );
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean createUser(String username, String email, String photoProfile, String password) {
        try {
            PreparedStatement statement = this.connectionProvider
                    .getConnection()
                    .prepareStatement("INSERT INTO r_user(username, email, photo_profile, user_password) VALUES (?, ?, ?, ?)");
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, photoProfile);
            statement.setString(4, password);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public User getUserByID(int userID) {
        try {
            PreparedStatement statement = this.connectionProvider
                    .getConnection()
                    .prepareStatement("SELECT * FROM r_user WHERE id = ?");
            statement.setInt(1, userID);
            ResultSet data = statement.executeQuery();
            data.next();
            return new User(
                    data.getInt("id"),
                    data.getString("username"),
                    data.getString("email"),
                    data.getString("photo_profile"),
                    data.getString("user_password")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getSubscriptionsByUserID(Integer userID) throws DatabaseException {
        try {
            PreparedStatement statement = this.connectionProvider
                    .getConnection()
                    .prepareStatement("SELECT u.* FROM r_user as u inner join subscription as s on u.id = s.fk_author_id where s.fk_subscriber_id = ?;");
            statement.setInt(1, userID);
            ResultSet data = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (data.next()) {
                User user = new User(
                        data.getInt("id"),
                        data.getString("username"),
                        data.getString("photo_profile")
                );
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new DatabaseException("Can't get authors list from DB", e);
        }
    }

    public boolean deleteUser(Integer userId) {
        try {
            PreparedStatement statement = this.connectionProvider
                    .getConnection()
                    .prepareStatement("DELETE FROM r_user WHERE id = ?");
            statement.setInt(1, userId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAuthorsNotSubscribedByUserID(Integer userID) throws DatabaseException {
        try {
            PreparedStatement statement = this.connectionProvider
                    .getConnection()
                    .prepareStatement("SELECT u2.* FROM r_user AS u2 FULL JOIN (\n" +
                            "    SELECT u.* FROM r_user AS u INNER JOIN subscription AS s\n" +
                            "        ON u.id = s.fk_author_id\n" +
                            "               WHERE s.fk_subscriber_id = ?\n" +
                            ") AS \"usub.*\"\n" +
                            "    ON u2.id = \"usub.*\".id\n" +
                            "         WHERE \"usub.*\".id IS NULL\n" +
                            "           AND u2.id != ?;");
            statement.setInt(1, userID);
            statement.setInt(2, userID);
            ResultSet data = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (data.next()) {
                User user = new User(
                        data.getInt("id"),
                        data.getString("username"),
                        data.getString("photo_profile")
                );
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new DatabaseException("Can't get authors list from DB", e);
        }
    }
}
