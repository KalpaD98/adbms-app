package com.adbms.app.repositories;

import com.adbms.app.models.Supplier.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SupplierRepository extends MongoRepository<Supplier, String> {
}
