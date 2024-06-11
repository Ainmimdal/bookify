package com.mimdal.bookify.data;

import com.mimdal.bookify.models.Order;
import com.mimdal.bookify.models.User;

import java.util.ArrayList;
import java.util.List;


public class OrderData {
    private static List<Order> orders = new ArrayList<>();

    public static void addOrder(Order order) {
        orders.add(order);
    }

    public static List<Order> getOrdersByUser(User user) {
        List<Order> userOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getUser().getUsername().equals(user.getUsername())) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }

    public static List<Order> getOrders() {
        return orders;
    }
}