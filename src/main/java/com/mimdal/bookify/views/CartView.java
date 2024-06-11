package com.mimdal.bookify.views;

import com.mimdal.bookify.controllers.CartController;
import com.mimdal.bookify.models.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class CartView {
    private CartController controller;
    private BorderPane view;
    private ListView<Book> cartListView;
    private Label totalLabel;

    public CartView() {
        view = new BorderPane();
        cartListView = new ListView<>();
        totalLabel = new Label("Total: $0.00");

        Button checkoutButton = new Button("Checkout");
        checkoutButton.setOnAction(e -> controller.checkout());

        VBox vBox = new VBox(10, cartListView, totalLabel, checkoutButton);
        vBox.setPadding(new Insets(10));

        view.setCenter(vBox);
    }

    public void setController(CartController controller) {
        this.controller = controller;
    }

    public void setCartItems(ObservableList<Book> cartItems) {
        cartListView.setItems(cartItems);
        updateTotal();
    }

    private void updateTotal() {
        double total = cartListView.getItems().stream().mapToDouble(Book::getPrice).sum();
        totalLabel.setText("Total: $" + String.format("%.2f", total));
    }

    public BorderPane getView() {
        return view;
    }

    public void showConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}