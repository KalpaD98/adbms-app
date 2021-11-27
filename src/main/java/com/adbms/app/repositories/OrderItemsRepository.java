package com.adbms.app.repositories;

import com.adbms.app.models.OrderItems.OrderItems;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderItemsRepository extends MongoRepository<OrderItems, String> {

    OrderItems getOrderItemsById(String id);
}