package com.adbms.app.models.OrderItems;

import com.adbms.app.models.Sku.*;
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

    @Min(3)
    @Getter
    @Setter
    private String itemName;

    @Getter
    @Setter
    private double quantity;

    @Getter
    @Setter
    private double unitPrice;

//    public OrderItems() {
//
//        sku = new Sku().getSku();
//        unitPrice = new Sku().getUnitPrice();
//    }
}
