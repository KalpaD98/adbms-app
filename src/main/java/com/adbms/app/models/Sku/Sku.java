package com.adbms.app.models.Sku;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@ToString
@Document
public class Sku {
    @Id
    private String id;
    private String brand;
    private String model;
    private String material;
    private int size;
    private String color;
    private String sku;
    private String name;
    private Double unitPrice;
}
