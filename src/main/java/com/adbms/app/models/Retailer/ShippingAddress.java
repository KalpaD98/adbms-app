package com.adbms.app.models.Retailer;

import com.adbms.app.models.shared.CommonAddress;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ShippingAddress extends CommonAddress {
    public ShippingAddress() {
    }
}
