package com.mimdal.bookify.views;

import com.mimdal.bookify.controllers.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class LoginView {
    private VBox view;
    private LoginController controller;
    private TextField usernameField;
    private PasswordField passwordField;
    private Label messageLabel;

    public LoginView() {
        createView();
    }

    private void createView() {
        view = new VBox();
        view.setAlignment(Pos.CENTER);
        view.setPadding(new Insets(50));
        view.setBackground(new Background(new BackgroundFill(Color.rgb(240, 240, 240), CornerRadii.EMPTY, Insets.EMPTY)));

        Label titleLabel = new Label("Welcome to Bookify!");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label usernameLabel = new Label("Username:");
        usernameField = new TextField();
        gridPane.addRow(0, usernameLabel, usernameField);

        Label passwordLabel = new Label("Password:");
        passwordField = new PasswordField();
        gridPane.addRow(1, passwordLabel, passwordField);

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");

        VBox buttonsBox = new VBox(10, loginButton, registerButton, messageLabel);
        buttonsBox.setAlignment(Pos.CENTER);

        view.getChildren().addAll(titleLabel, gridPane, buttonsBox);

        // Button actions
        loginButton.setOnAction(e -> {
            if (controller != null) {
                controller.handleLogin(usernameField.getText(), passwordField.getText());
            }
        });

        registerButton.setOnAction(e -> {
            if (controller != null) {
                controller.handleRegister(usernameField.getText(), passwordField.getText(), "email@example.com");
            }
        });
    }

    public VBox getView() {
        return view;
    }

    public void setController(LoginController controller) {
        this.controller = controller;
    }

    public void showMessage(String message) {
        messageLabel.setText(message);
    }

    public void showError(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(error);
        alert.showAndWait();
    }
}