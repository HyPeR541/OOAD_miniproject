package com.ooad6.ecommerce.repository;

import com.ooad6.ecommerce.model.Discount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends MongoRepository<Discount, String> {
    Discount findByCouponName(String couponName);
}
