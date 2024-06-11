package com.mimdal.bookify.controllers;

import com.mimdal.bookify.Utility.UserUtility;
import com.mimdal.bookify.data.OrderData;
import com.mimdal.bookify.models.Book;
import com.mimdal.bookify.models.Order;
import com.mimdal.bookify.models.User;
import com.mimdal.bookify.views.BookListView;
import com.mimdal.bookify.views.CartView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
public class CartController {
    private CartView view;
    private ObservableList<Book> cartItems;

    public CartController() {

        this.cartItems = FXCollections.observableArrayList();
        //view.setCartItems(cartItems);
    }
     public void setView(CartView view){
        this.view= view;
     }

    public void addToCart(Book book) {
        cartItems.add(book);
        view.setCartItems(cartItems);
    }

    public void checkout() {
        // Handle checkout logic
        // For demonstration, just clear the cart and show confirmation
        cartItems.clear();
       // view.setCartItems(cartItems);
        Order order = new Order(UserUtility.currentUser,cartItems);
        OrderData.addOrder(order);
        //view.showConfirmation("Your order has been placed successfully!");
    }

    public CartView getView() {
        return view;
    }
}

