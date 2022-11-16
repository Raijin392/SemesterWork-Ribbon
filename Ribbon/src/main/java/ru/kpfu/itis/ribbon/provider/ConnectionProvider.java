package ru.kpfu.itis.ribbon.provider;

import ru.kpfu.itis.ribbon.Exception.DatabaseException;
import ru.kpfu.itis.ribbon.Exception.PropertiesException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {

    private static ConnectionProvider _instance;

    public static ConnectionProvider getInstance() throws DatabaseException, PropertiesException {
        if (_instance == null) {
            _instance = new ConnectionProvider();
        }
        return _instance;
    }

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    private Properties properties = new Properties();

    private ConnectionProvider() throws DatabaseException, PropertiesException {
        try {
            properties.load(ConnectionProvider.class.getResourceAsStream("/config.properties"));
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.username"),
                    properties.getProperty("db.password")
            );
        } catch (SQLException | ClassNotFoundException e) {
            throw new DatabaseException("Can't connect to Database", e);
        } catch (IOException e) {
            throw new PropertiesException("Properties not found", e);
        }
    }



}
