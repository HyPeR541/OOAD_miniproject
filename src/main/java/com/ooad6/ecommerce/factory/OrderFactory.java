package com.ooad6.ecommerce.factory;

import com.ooad6.ecommerce.model.Cart;
import com.ooad6.ecommerce.model.Orders;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Component
public class OrderFactory {
    public Orders createOrder(int userId, List<Cart> cartItems, String paymentMethod) {
        Orders order = new Orders();
        order.setOrderId(new Random().nextInt(900000) + 100000); 
        order.setUserId(userId);
        order.setProdList(cartItems);
        order.setPaymentMethod(paymentMethod);
        order.setTimestamp(LocalDateTime.now());
        return order;
    }
}
