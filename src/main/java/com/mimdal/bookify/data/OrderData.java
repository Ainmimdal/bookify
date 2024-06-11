package com.mimdal.bookify.data;

import com.mimdal.bookify.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderData {
    private static List<Order> orders = new ArrayList<>();

    public static void addOrder(Order order) {
        orders.add(order);
    }

    public static List<Order> getOrdersByUsername(String username) {
        List<Order> userOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getUsername().equalsIgnoreCase(username)) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }

    public static void updateOrderStatus(Order order, String status) {
        order.setStatus(status);
    }
}