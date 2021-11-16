package com.adbms.app.models.Retailer;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public abstract class CommonAddress {

    @Getter
    @Setter
    @NotBlank(message = "Street 1 cannot be empty")
    private String street1;

    @Getter
    @Setter
    private String street2;

    @Getter
    @Setter
    @NotBlank(message = "City cannot be empty")
    private String city;

    @Getter
    @Setter
    @NotBlank(message = "District cannot be empty")
    private String district;

    @Getter
    @Setter
    @NotBlank(message = "Zip cannot be empty")
    private int zip;

    @Override
    public String toString() {
        return "CommonAddress{" +
                "street1='" + street1 + '\'' +
                ", street2='" + street2 + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", zip=" + zip +
                '}';
    }
}
