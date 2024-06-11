package com.mimdal.bookify.views;

import com.mimdal.bookify.controllers.BookListController;
import com.mimdal.bookify.models.Book;
import com.mimdal.bookify.models.Order;
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
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setBackground(new Background(new BackgroundFill(Color.rgb(240, 240, 240), CornerRadii.EMPTY, Insets.EMPTY)));

        Label titleLabel = new Label("Order History");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        int row = 0;
        for (Order order : orders) {
            Label orderLabel = new Label("Order " + order.getId() + " - Total: $" + order.getTotalAmount());
            orderLabel.setStyle("-fx-text-fill: #333333;");
            gridPane.addRow(row++, orderLabel);
        }

        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");

        closeButton.setOnAction(e -> stage.close());

        root.getChildren().addAll(titleLabel, gridPane, closeButton);

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.showAndWait();
    }
}