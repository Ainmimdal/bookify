package com.mimdal.bookify.data;

import com.mimdal.bookify.models.User;

import java.util.HashMap;
import java.util.Map;
public class UserData {
    private static Map<String, User> users = new HashMap<>();

    public static void addUser(User user) throws Exception {
        if (users.containsKey(user.getUsername())) {
            throw new Exception("User already exists.");
        }
        users.put(user.getUsername(), user);
    }

    public static User getUser(String username, String password) throws Exception {
        User user = users.get(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new Exception("Invalid username or password.");
        }
        return user;
    }
}