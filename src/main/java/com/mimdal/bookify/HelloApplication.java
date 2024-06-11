package com.mimdal.bookify;

import com.mimdal.bookify.controllers.LoginController;
import com.mimdal.bookify.models.Book;
import com.mimdal.bookify.views.BookListView;
import com.mimdal.bookify.views.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bookify - Online Bookstore Management System");
        //primaryStage.setFullScreen(true);

        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(loginView);
        loginView.setController(loginController);

        Scene scene = new Scene(loginView.getView(), Color.rgb(240, 240, 240));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}