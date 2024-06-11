package com.mimdal.bookify;

import com.mimdal.bookify.controllers.LoginController;
import com.mimdal.bookify.views.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bookify - Online Bookstore Management System");
        //primaryStage.setFullScreen(true);


        LoginController loginController = new LoginController(primaryStage);
        loginController.showLoginView();
        LoginView loginView = new LoginView(primaryStage,loginController);
      //  Scene scene = new Scene(loginView, Color.rgb(240, 240, 240));
       // primaryStage.setScene(scene);
//primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}