package com.mimdal.bookify.views;

import com.mimdal.bookify.controllers.BookListController;
import com.mimdal.bookify.models.Book;
import com.mimdal.bookify.models.Order;
import com.mimdal.bookify.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class OrderListView {
    private List<Order> orders;
    private BookListController controller;

    public OrderListView(List<Order> orders, BookListController controller) {
        this.orders = orders;
        this.controller = controller;
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Order History");
        stage.initModality(Modality.APPLICATION_MODAL);

        VBox root = new VBox(10);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(20));
        root.setBackground(new Background(new BackgroundFill(Color.rgb(240, 240, 240), CornerRadii.EMPTY, Insets.EMPTY)));

        Label titleLabel = new Label("Order History");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        VBox ordersBox = new VBox(10);
        ordersBox.setAlignment(Pos.CENTER);
        ordersBox.setPadding(new Insets(10));
        ordersBox.setStyle("-fx-background-color: white; -fx-border-color: #CCCCCC; -fx-border-radius: 5px;");

        for (Order order : orders) {
            VBox orderBox = new VBox(5);
            orderBox.setAlignment(Pos.CENTER_LEFT);
            orderBox.setPadding(new Insets(10));
            orderBox.setStyle("-fx-background-color: #F9F9F9; -fx-border-color: #DDDDDD; -fx-border-radius: 5px;");

            Label orderLabel = new Label("Order " + order.getId() + " - Total: $" + order.getTotalAmount());
            orderLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;");

            VBox booksBox = new VBox(5);
            for (Book book : order.getBooks()) {
                Label bookLabel = new Label(book.getTitle() + " by " + book.getAuthor() + " - $" + book.getPrice());
                bookLabel.setStyle("-fx-text-fill: #555555;");
                booksBox.getChildren().add(bookLabel);
            }

            orderBox.getChildren().addAll(orderLabel, booksBox);
            ordersBox.getChildren().add(orderBox);
        }

        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");

        closeButton.setOnAction(e -> stage.close());

        root.getChildren().addAll(titleLabel, ordersBox, closeButton);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
