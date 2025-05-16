package com.ooad6.ecommerce.controller;

import com.ooad6.ecommerce.model.Orders;
import com.ooad6.ecommerce.model.User;
import com.ooad6.ecommerce.repository.OrdersRepository;
import com.ooad6.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/adminDashboard")
public class AdminDashboardController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrdersRepository ordersRepository; // ✅ Added to fetch orders

    @GetMapping
    public String viewAdminDashboard(Model model) {
        List<User> users = userRepository.findAll();
        System.out.println("Fetched Users: " + users); // Debugging Log
        model.addAttribute("users", users);
        return "adminDashboard";  // Ensure this matches the JSP filename
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("userid") Integer userid) {
        userRepository.findByuserId(userid).ifPresent(user -> {
            if (!user.getName().toLowerCase().contains("admin")) {
                userRepository.delete(user);
            }
        });
        return "redirect:/adminDashboard";
    }

    // ✅ New method to view order history
    @GetMapping("/order-history")
    public String viewAllOrderHistory(Model model) {
        List<Orders> allOrders = ordersRepository.findAll();
        model.addAttribute("allOrders", allOrders);
        return "orderhistory";  // maps to /views/orderhistory.jsp
    }
}
