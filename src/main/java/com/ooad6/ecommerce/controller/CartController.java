package com.ooad6.ecommerce.controller;

import com.ooad6.ecommerce.model.Cart;
import com.ooad6.ecommerce.model.Items;
import com.ooad6.ecommerce.repository.CartRepository;
import com.ooad6.ecommerce.repository.ItemsShow;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class CartController {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemsShow itemRepository;

    @RequestMapping("/cart")
    public String cart(Model model, HttpSession session) {
        Object userIdObj = session.getAttribute("userid");
        int userId = (Integer) userIdObj;
        List<Cart> cartList = cartRepository.findByUserid(userId);
        model.addAttribute("cartItems", cartList);

        int totalCost = 0;
        for (Cart item : cartList) {
            totalCost += item.getCost() * item.getQty();
        }
        model.addAttribute("totalCost", totalCost);
        session.setAttribute("totalCost", totalCost);

        return "cart";
    }

    @PostMapping("/updateCartQty")
    @ResponseBody
    public int updateCartQty(@RequestParam String itemId, @RequestParam int change) {
        Optional<Cart> cartItemOpt = cartRepository.findById(itemId);
        Optional<Items> itemOpt = itemRepository.findById(itemId);

        if (cartItemOpt.isPresent() && itemOpt.isPresent()) {
            Cart cartItem = cartItemOpt.get();
            Items item = itemOpt.get();

            int newQty = cartItem.getQty() + change;
            int newStock = item.getStock() - change; // Adjust stock accordingly

            if (newQty < 1 || newStock < 0) return getTotalCartCost();  // Prevent negative values

            cartItem.setQty(newQty);
            cartRepository.save(cartItem);

            item.setStock(newStock);
            itemRepository.save(item); // Update stock in MongoDB
        }

        return getTotalCartCost();
    }

    @PostMapping("/clearCart")
    public String clearCart() {
        List<Cart> cartList = cartRepository.findAll();

        // Restore stock for each item before clearing cart
        for (Cart cartItem : cartList) {
            Optional<Items> itemOpt = itemRepository.findById(cartItem.getId());
            if (itemOpt.isPresent()) {
                Items item = itemOpt.get();
                item.setStock(item.getStock() + cartItem.getQty()); // Restore stock
                itemRepository.save(item);
            }
        }

        // Clear the cart
        cartRepository.deleteAll();

        return "redirect:/cart";
    }

    private int getTotalCartCost() {
        List<Cart> cartList = cartRepository.findAll();
        return cartList.stream().mapToInt(item -> item.getCost() * item.getQty()).sum();
    }

}
