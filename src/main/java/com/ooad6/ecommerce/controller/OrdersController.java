package com.ooad6.ecommerce.controller;

import com.ooad6.ecommerce.factory.OrderFactory;
import com.ooad6.ecommerce.model.Cart;
import com.ooad6.ecommerce.model.Orders;
import com.ooad6.ecommerce.observer.OrderObserver;
import com.ooad6.ecommerce.repository.CartRepository;
import com.ooad6.ecommerce.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrdersController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderFactory orderFactory;

    @Autowired
    private List<OrderObserver> observers;

    @GetMapping("/orders")
    public String showOrders(Model model, HttpSession session) {
        Object userIdObj = session.getAttribute("userid");

        if (userIdObj instanceof Integer) {
            int userId = (Integer) userIdObj;
            List<Cart> cartItems = cartRepository.findByUserid(userId);
            session.setAttribute("cartItems", cartItems);
            model.addAttribute("cartItems", cartItems);
            return "orders";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/confirmOrder")
    public String confirmOrder(@RequestParam("paymentMethod") String paymentMethod, HttpSession session) {
        Object userIdObj = session.getAttribute("userid");

        if (userIdObj instanceof Integer) {
            int userId = (Integer) userIdObj;
            List<Cart> cartItems = (List<Cart>) session.getAttribute("cartItems");

            if (cartItems == null || cartItems.isEmpty()) {
                session.setAttribute("orderMessage", "Your cart is empty. Add items before confirming the order.");
                return "redirect:/orders";
            }

            // 🔥 Use the factory to create the order
            Orders newOrder = orderFactory.createOrder(userId, cartItems, paymentMethod);
            ordersRepository.save(newOrder);

            for (OrderObserver observer : observers) {
                observer.onOrderPlaced(newOrder);
            }

            cartRepository.deleteAll(cartItems);
            session.removeAttribute("cartItems");

            session.setAttribute("orderMessage", "Order placed successfully! Order ID: " + newOrder.getOrderId());
            return "thankyou";
        } else {
            return "redirect:/login";
        }
    }
}
