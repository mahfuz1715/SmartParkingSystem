package main.controller;

import main.dao.UserDao;
import main.model.User;

public class LoginController {
    private UserDao userDao = new UserDao();

    public User login(String username, String password) {
        return userDao.login(username, password);
    }

    public boolean register(String username, String password, String role) {
        User user = new User(0, username, password, role);
        return userDao.createUser(user);
    }
}
