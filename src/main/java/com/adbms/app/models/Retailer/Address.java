package com.adbms.app.models.Retailer;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document
public class Address extends CommonAddress {
    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    @NotBlank(message = "Country cannot be empty")
    private String country;

    public Address() {
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
