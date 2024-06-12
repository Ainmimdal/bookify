package com.mimdal.bookify.views;

import com.mimdal.bookify.controllers.BookListController;
import com.mimdal.bookify.controllers.CartController;
import com.mimdal.bookify.models.Book;
import com.mimdal.bookify.models.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
public class CartView {
    private List<Book> cartItems;
    private CartController controller;

    public CartView(CartController controller) {
        this.controller = controller;
        this.controller.setView(this);
        this.cartItems = new ArrayList<>();
    }

    public void addToCart(Book book) {
        cartItems.add(book);
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Shopping Cart");

        VBox root = new VBox(10);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(20));
        root.setBackground(new Background(new BackgroundFill(Color.rgb(240, 240, 240), CornerRadii.EMPTY, Insets.EMPTY)));

        Label titleLabel = new Label("Shopping Cart");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        VBox cartItemsBox = new VBox(10);
        cartItemsBox.setAlignment(Pos.CENTER_LEFT);
        cartItemsBox.setPadding(new Insets(10));
        cartItemsBox.setStyle("-fx-background-color: #F9F9F9; -fx-border-color: #DDDDDD; -fx-border-radius: 5px;");

        for (Book book : cartItems) {
            HBox bookInfoBox = new HBox(10);
            bookInfoBox.setAlignment(Pos.CENTER_LEFT);

            Label bookLabel = new Label(book.getTitle() + " by " + book.getAuthor() + " - $" + book.getPrice());
            bookLabel.setStyle("-fx-text-fill: #333333;");

            Button removeButton = new Button("Remove");
            removeButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");
            removeButton.setOnAction(e -> {
                cartItems.remove(book);
                refreshCartItems(cartItemsBox);
            });

            bookInfoBox.getChildren().addAll(bookLabel, removeButton);
            cartItemsBox.getChildren().add(bookInfoBox);
        }

        Button placeOrderButton = new Button("Place Order");
        placeOrderButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");

        HBox buttonsBox = new HBox(10, placeOrderButton, closeButton);
        buttonsBox.setAlignment(Pos.BOTTOM_CENTER);

        placeOrderButton.setOnAction(e -> {
            controller.checkout();
            stage.close();
        });

        closeButton.setOnAction(e -> stage.close());

        root.getChildren().addAll(titleLabel, cartItemsBox, buttonsBox);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void setCartItems(List<Book> cartItems) {
        this.cartItems = cartItems;
    }

    private void refreshCartItems(VBox cartItemsBox) {
        cartItemsBox.getChildren().clear();
        for (Book book : cartItems) {
            HBox bookInfoBox = new HBox(10);
            bookInfoBox.setAlignment(Pos.CENTER_LEFT);

            Label bookLabel = new Label(book.getTitle() + " by " + book.getAuthor() + " - $" + book.getPrice());
            bookLabel.setStyle("-fx-text-fill: #333333;");

            Button removeButton = new Button("Remove");
            removeButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");
            removeButton.setOnAction(e -> {
                cartItems.remove(book);
                refreshCartItems(cartItemsBox);
            });

            bookInfoBox.getChildren().addAll(bookLabel, removeButton);
            cartItemsBox.getChildren().add(bookInfoBox);
        }
    }

    public void showConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
