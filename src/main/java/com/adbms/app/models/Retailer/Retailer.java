package com.adbms.app.models.Retailer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Retailer {
    @Id
    @Getter
    @Setter
    private String id;

    private String firstName;

    private String lastName;

    private String email;

}
