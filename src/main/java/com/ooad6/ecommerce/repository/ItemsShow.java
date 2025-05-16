package com.ooad6.ecommerce.repository;

import com.ooad6.ecommerce.model.Items;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsShow extends MongoRepository<Items, String> {
}