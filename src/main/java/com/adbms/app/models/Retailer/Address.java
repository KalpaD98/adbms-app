package com.adbms.app.models.Retailer;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Address extends CommonAddress{

    private String country;
}
