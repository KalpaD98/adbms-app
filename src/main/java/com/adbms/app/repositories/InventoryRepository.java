package com.adbms.app.repositories;

import com.adbms.app.models.Inventory.Inventory;
import com.adbms.app.models.order.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
}
