package com.mimdal.bookify;

import com.mimdal.bookify.controllers.LoginController;
import com.mimdal.bookify.views.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create the login controller and show the login view
        LoginController loginController = new LoginController(primaryStage);
        loginController.showLoginView();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
