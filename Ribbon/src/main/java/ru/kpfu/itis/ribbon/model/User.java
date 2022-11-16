package ru.kpfu.itis.ribbon.model;

import java.util.Objects;

public class User {
    private Integer id;
    private String username;
    private String email;
    private String photoProfile;
    private String userPassword;

    public User(Integer id, String username, String email, String photoProfile, String userPassword) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.photoProfile = photoProfile;
        this.userPassword = userPassword;
    }

    public User(String username, String email, String photoProfile, String userPassword) {
        this.username = username;
        this.email = email;
        this.photoProfile = photoProfile;
        this.userPassword = userPassword;
    }

    public User(Integer id, String username, String photoProfile) {
        this.id = id;
        this.username = username;
        this.photoProfile = photoProfile;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhotoProfile() {
        return photoProfile;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhotoProfile(String photoProfile) {
        this.photoProfile = photoProfile;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!Objects.equals(username, user.username)) return false;
        if (!Objects.equals(email, user.email)) return false;
        if (!Objects.equals(photoProfile, user.photoProfile)) return false;
        return Objects.equals(userPassword, user.userPassword);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (photoProfile != null ? photoProfile.hashCode() : 0);
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        return result;
    }
}

