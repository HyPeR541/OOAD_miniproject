package com.ooad6.ecommerce.repository;

import com.ooad6.ecommerce.model.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends MongoRepository<Orders, String> {
}
