package com.mimdal.bookify.views;

import com.mimdal.bookify.models.Book;
import com.mimdal.bookify.models.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.List;

public class OrderListView {
    private VBox view;
    private ObservableList<Order> orders;

    public OrderListView() {
        orders = FXCollections.observableArrayList();
        createView();
    }

    private void createView() {
        view = new VBox();
        view.setAlignment(Pos.CENTER);
        view.setSpacing(10);
        view.setPadding(new Insets(10));

        ListView<Order> orderListView = new ListView<>(orders);
        orderListView.setCellFactory(param -> new OrderCell());

        view.getChildren().addAll(orderListView);
    }

    public VBox getView() {
        return view;
    }

    public void setOrders(List<Order> orders) {
        this.orders.setAll(orders);
    }

    private static class OrderCell extends javafx.scene.control.ListCell<Order> {
        @Override
        protected void updateItem(Order order, boolean empty) {
            super.updateItem(order, empty);
            if (empty || order == null) {
                setText(null);
            } else {
                StringBuilder booksInfo = new StringBuilder();
                for (Book book : order.getBooks()) {
                    booksInfo.append(book.getTitle()).append(" by ").append(book.getAuthor()).append(" - $").append(book.getPrice()).append("\n");
                }
                setText("Order by " + order.getUsername() + ":\n" + booksInfo.toString() + "Status: " + order.getStatus());
            }
        }
    }
}