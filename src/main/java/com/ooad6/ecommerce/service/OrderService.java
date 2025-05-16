package com.ooad6.ecommerce.service;

import com.ooad6.ecommerce.observer.OrderObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ooad6.ecommerce.model.Orders;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final List<OrderObserver> observers = new ArrayList<>();

    @Autowired
    public OrderService(List<OrderObserver> allObservers) {
        observers.addAll(allObservers); // Automatically injects all observer beans
    }

    public void placeOrder(Orders order) {
        System.out.println("Placing Order: " + order.getOrderId());

        // Notify all observers
        for (OrderObserver observer : observers) {
            observer.onOrderPlaced(order);
        }
    }
}
