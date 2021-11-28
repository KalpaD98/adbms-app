package com.adbms.app.models.Supplier;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.awt.geom.Area;

public class Supplier {
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
    private String address;

    @Getter
    @Setter
    @NotBlank
    private String material;

    @Getter
    @Setter
    @NotBlank
    private String area;

    @Getter
    @Setter
    @NotBlank
    private String details;


    public Supplier() {
    }

}
