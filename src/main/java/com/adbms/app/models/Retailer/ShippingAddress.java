package com.adbms.app.models.Retailer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ShippingAddress extends CommonAddress {
    @Id
    @Getter
    @Setter
    private String id;

    public ShippingAddress() {
    }

    @Override
    public String toString() {
        return "ShippingAddress{" +
                "id='" + id + '\'' +
                '}';
    }
}
