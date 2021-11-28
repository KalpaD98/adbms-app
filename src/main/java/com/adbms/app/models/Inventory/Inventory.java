package com.adbms.app.models.Inventory;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Document
public class Inventory {
    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    @NotBlank
    private String inventoryId;


    @NotBlank(message = "Item name cannot be empty")
    @Min(3)
    @Getter
    @Setter
    private String name;


    @Getter
    @Setter
    @NotBlank
    private String features;

    @Getter
    @Setter
    @NotBlank
    private String category;

    @Getter
    @Setter
    @NotBlank
    private int quantity;

}
