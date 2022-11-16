package ru.kpfu.itis.ribbon.service;

import ru.kpfu.itis.ribbon.DAO.UserDAO;
import ru.kpfu.itis.ribbon.Encryptors.Encryptor;
import ru.kpfu.itis.ribbon.Exception.DatabaseException;
import ru.kpfu.itis.ribbon.Exception.EncryptorException;
import ru.kpfu.itis.ribbon.Exception.PropertiesException;
import ru.kpfu.itis.ribbon.provider.ConnectionProvider;
import ru.kpfu.itis.ribbon.model.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {

    private static final String SALT = "YFJ32ruw49iPE8OHtf764WQ45e3";
    private static final String newUserPhoto = "profile.jpg";

    private final UserDAO userDAO;

    public UserService() throws PropertiesException, DatabaseException {
        ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
        userDAO = new UserDAO(connectionProvider);
    }

    public User getUserByEmailAndPassword(HttpServletRequest req, HttpServletResponse resp) throws EncryptorException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        ServletContext servletContext = req.getServletContext();
        String pass = hashPassword(password);
        User user = userDAO.getUserByEmailAndPassword(email, pass);
        if (user == null) {
            servletContext.setAttribute("errorUncorrectEmailOrPassword", "Wrong email or password");
            return null;
        } else {
            servletContext.removeAttribute("errorUncorrectEmailOrPassword");
            return user;
        }
    }

    public void deleteUser(Integer userId) {
        userDAO.deleteUser(userId);
    }

    private User getUserByEmailAndPassword(String email, String password) throws EncryptorException {
        String pass = hashPassword(password);
        return userDAO.getUserByEmailAndPassword(email, pass);
    }

    public List<User> getSubscriptionsByUserID(Integer userID) {
        try {
            return userDAO.getSubscriptionsByUserID(userID);
        } catch (DatabaseException e) {
            return null;
        }
    }

    public List<User> getAuthorsNotSubscribedByUserID(Integer userID) {
        try {
            return userDAO.getAuthorsNotSubscribedByUserID(userID);
        } catch (DatabaseException e) {
            return null;
        }
    }

    public boolean createUser(HttpServletRequest req, HttpServletResponse resp) throws EncryptorException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        ServletContext servletContext = req.getServletContext();

        User user = getUserByEmailAndPassword(email, password);
        if (user == null) {
            boolean allCorrect = true;
            if (!usernameValidate(username)) {
                servletContext.setAttribute("errorUncorrectUsername", "Username entered incorrectly. The length must be between 3 and 128 and contain only \"_ . -\" special characters.");
                allCorrect = false;
            } else {
                servletContext.removeAttribute("errorUncorrectUsername");
            }
            if (!emailValidate(email)) {
                servletContext.setAttribute("errorUncorrectEmail", "Email entered incorrectly");
                allCorrect = false;
            } else {
                servletContext.removeAttribute("errorUncorrectEmail");
            }
            if (!passwordValidate(password)) {
                servletContext.setAttribute("errorUncorrectPassword", "Password entered incorrectly. The length must be between 3 and 128 and must not contain special characters.");
                allCorrect = false;
            } else {
                servletContext.removeAttribute("errorUncorrectPassword");
            }
            if (allCorrect) {
                String pass = hashPassword(password);
                allCorrect = userDAO.createUser(username, email, newUserPhoto, pass);
            }
            return allCorrect;
        } else  {
            servletContext.setAttribute("errorUserExists", "User with this email already exists, try to login");
            return false;
        }

    }

    public User getUserByID(int userID) {
        return userDAO.getUserByID(userID);
    }

    private String hashPassword(String password) throws EncryptorException {
        try {
            return Encryptor.hashPassword(password, SALT).get().toString();
        } catch (EncryptorException e) {
            throw new EncryptorException("Encryptor ex", e);
        }
    }

    private boolean emailValidate(String email) {
        Pattern pattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean usernameValidate(String username) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9_.-]{3,128}");
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    private boolean passwordValidate(String password) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]{3,128}");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
