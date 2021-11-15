package com.adbms.app.models.Retailer;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class Address extends CommonAddress {
    @Getter
    @Setter
    @NotBlank(message = "Country cannot be empty")
    private String country;
}
