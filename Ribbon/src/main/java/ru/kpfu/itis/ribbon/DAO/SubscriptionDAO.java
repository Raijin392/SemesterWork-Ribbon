package ru.kpfu.itis.ribbon.DAO;

import ru.kpfu.itis.ribbon.provider.ConnectionProvider;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SubscriptionDAO {

    final private ConnectionProvider connectionProvider;

    public SubscriptionDAO(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public boolean deleteSubscription(Integer authorID, Integer subscriberID) {
        try {
            PreparedStatement statement = this.connectionProvider
                    .getConnection()
                    .prepareStatement("DELETE FROM subscription WHERE fk_author_id = ? and fk_subscriber_id = ?;");
            statement.setInt(1, authorID);
            statement.setInt(2, subscriberID);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean offerSubscription(Integer authorID, Integer subscriberID) {
        try {
            PreparedStatement statement = this.connectionProvider
                    .getConnection()
                    .prepareStatement("INSERT INTO subscription(fk_author_id, fk_subscriber_id) VALUES (?, ?);");
            statement.setInt(1, authorID);
            statement.setInt(2, subscriberID);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }


}
