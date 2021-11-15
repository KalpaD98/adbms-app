package com.adbms.app.models;

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


}
