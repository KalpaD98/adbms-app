package com.adbms.app.repositories;

import com.adbms.app.models.OrderItems.OrderItems;
import com.adbms.app.models.Sku.Sku;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkuRepository extends MongoRepository<Sku, String> {

    Sku getSkuByName(String name);
}
