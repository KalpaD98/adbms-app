package com.adbms.app.models.OrderItems;

import com.adbms.app.models.Sku.Sku;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Document
public class OrderItems {
    @Id
    @Getter
    @Setter
    private String id;

    @NotBlank
    @Getter
    @Setter
    private String sku;

    @NotBlank(message = "Item name cannot be empty")
    @Min(3)
    @Getter
    @Setter
    private String itemName;

    @NotBlank(message = "Quantity cannot be empty")
    @Getter
    @Setter
    private int quantity;

    @NotBlank
    @Getter
    @Setter
    private double unitPrice;

    public OrderItems() {
        sku = new Sku().getSku();
        unitPrice = new Sku().getUnitPrice();
    }
}
