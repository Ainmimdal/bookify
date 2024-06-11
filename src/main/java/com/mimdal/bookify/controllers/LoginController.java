package com.mimdal.bookify.controllers;


import com.mimdal.bookify.data.UserData;
import com.mimdal.bookify.models.Book;
import com.mimdal.bookify.models.User;
import com.mimdal.bookify.views.BookListView;
import com.mimdal.bookify.views.LoginView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {
    private LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
    }

    public void handleLogin(String username, String password) {
        try {
            User user = UserData.getUser(username, password);
            System.out.println("Logged in as: " + user.getUsername());

            BookListController bookListController = new BookListController();
            BookListView bookListView = new BookListView();
            bookListController.setView(bookListView);
            bookListView.setController(bookListController);

            Scene scene = new Scene(bookListView.getView(), 800, 600);
            Stage primaryStage = (Stage) loginView.getView().getScene().getWindow();
            primaryStage.setScene(scene);

        } catch (Exception e) {
            loginView.showMessage("Login failed: " + e.getMessage());
        }
    }

    public void handleRegister(String username, String password, String email) {
        try {
            User user = new User(username, password, email);
            UserData.addUser(user);
            loginView.showMessage("User registered successfully!");
        } catch (Exception e) {
            loginView.showMessage("Registration failed: " + e.getMessage());
        }
    }
}