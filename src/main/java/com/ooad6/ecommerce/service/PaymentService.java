package com.ooad6.ecommerce.service;

import com.ooad6.ecommerce.model.Orders;
import com.ooad6.ecommerce.observer.OrderObserver;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements OrderObserver {
    @Override
    public void onOrderPlaced(Orders order) {
        System.out.println("Payment successful for order: " + order.getOrderId());
    }
}

