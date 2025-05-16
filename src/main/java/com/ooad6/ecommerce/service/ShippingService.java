package com.ooad6.ecommerce.service;

import com.ooad6.ecommerce.model.Orders;
import com.ooad6.ecommerce.observer.OrderObserver;
import org.springframework.stereotype.Service;

@Service
public class ShippingService implements OrderObserver {
    @Override
    public void onOrderPlaced(Orders order) {
        System.out.println("Preparing shipment for order: " + order.getOrderId());
    }
}
