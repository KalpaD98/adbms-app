package com.adbms.app.models.Retailer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Document
public class Retailer {
    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    @NotBlank(message = "First name cannot be empty")
    @Min(3)
    private String name;

    @Email(message = "Email should be valid")
    @Indexed(unique = true)
    @Getter
    @Setter
    @Min(3)
    private String email;

//    @Getter
//    @Setter
//    @NotBlank
//    @DBRef
//    private Address address;
//
//    @Getter
//    @Setter
//    @NotBlank
//    @DBRef
//    private ShippingAddress shippingAddress;

    public Retailer() {
    }
}
