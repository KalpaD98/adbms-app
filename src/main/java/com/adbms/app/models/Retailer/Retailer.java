package com.adbms.app.models.Retailer;

import com.adbms.app.models.shared.Address;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
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


    @NotBlank(message = "First name cannot be empty")
    @Min(3)
    @Getter
    @Setter
    private String name;

    @Email(message = "Email should be valid")
    @Min(3)
    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    @NotBlank
    private Address address;


    @NotBlank
    @Getter
    @Setter
    private ShippingAddress shippingAddress;


    public Retailer() {
        address = new Address();
        shippingAddress = new ShippingAddress();
    }
}
