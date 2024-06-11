package com.mimdal.bookify.controllers;


import com.mimdal.bookify.Utility.UserUtility;
import com.mimdal.bookify.data.UserData;
import com.mimdal.bookify.models.Book;
import com.mimdal.bookify.models.User;
import com.mimdal.bookify.views.BookListView;
import com.mimdal.bookify.views.LoginView;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
public class LoginController {
    private Stage primaryStage;

    public LoginController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showLoginView() {
        LoginView loginView = new LoginView(primaryStage, this);
    }

    public void handleLogin(String username, String password) {
        try {
            User user = UserData.getUser(username, password);
            UserUtility.currentUser = user;
            System.out.println("Logged in as: " + user.getUsername());

            BookListView bookListView = new BookListView();
            BookListController bookListController = new BookListController(user);
            bookListView.setController(bookListController);
            bookListController.setBookListView(bookListView);

            Scene scene = new Scene(bookListView.getView(), 800, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Bookify - Book Catalog");

        } catch (Exception e) {
            showAlert("Login Failed", e.getMessage());
            //loginView.showMessage("Login failed: " + e.getMessage());
        }
    }


    public void handleRegister(String username, String password) {
        try {
            User user = new User(username, password);
            UserData.addUser(user);
            //loginView.showMessage("User registered successfully!");
            showAlert("Success" ,"User registered successfully");
        } catch (Exception e) {
            //loginView.showMessage("Registration failed: " + e.getMessage());
            showAlert("Registration failed" ,e.getMessage());
        }
    }

    private boolean authenticate(String username, String password) {
        // Simple authentication logic for demonstration purposes
        return "user".equals(username) && "password".equals(password);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}



