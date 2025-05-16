package com.ooad6.ecommerce.observer;

import com.ooad6.ecommerce.model.Orders;

public interface OrderObserver {
    void onOrderPlaced(Orders order);

}
