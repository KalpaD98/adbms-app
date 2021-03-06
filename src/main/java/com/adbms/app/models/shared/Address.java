package com.adbms.app.models.shared;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document
public class Address extends CommonAddress {

    @Getter
    @Setter
    @NotBlank(message = "Country cannot be empty")
    private String country;

    public Address() {
    }

}
