package com.ooad6.ecommerce.controller;

import com.ooad6.ecommerce.model.Discount;
import com.ooad6.ecommerce.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DiscountController {
    @Autowired
    private DiscountRepository discountRepository;

    @GetMapping("/validateCoupon")
    public Map<String, Object> validateCoupon(@RequestParam String couponName, @RequestParam double totalCost) {
        Map<String, Object> response = new HashMap<>();
        Discount coupon = discountRepository.findByCouponName(couponName);
        System.out.println(coupon);
        if (coupon == null) {
            response.put("valid", false);
            response.put("message", "invalid coupon name");
        }
        else {
            double discountAmount = totalCost * (coupon.getDiscountPercent() / 100.0);
            double finalAmount = totalCost - discountAmount;

            response.put("valid", true);
            response.put("discount", coupon.getDiscountPercent());
            response.put("discountAmount", discountAmount);
            response.put("finalAmount", finalAmount);
        }
        System.out.println(response);
        return response;
    }

}
