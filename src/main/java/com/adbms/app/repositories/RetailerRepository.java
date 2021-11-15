package com.adbms.app.repositories;

import com.adbms.app.models.Retailer.Retailer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RetailerRepository extends MongoRepository<Retailer, String> {

    Retailer getRetailerByEmail(String email);

    Retailer getRetailerByEmailLike(String email); //search
}
