package com.ooad6.ecommerce.repository;

import com.ooad6.ecommerce.model.Items;

import java.util.List;

public interface ItemsSearch {
    List<Items> findByText(String text );
}
