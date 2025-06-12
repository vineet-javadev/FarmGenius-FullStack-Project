package com.backend.AgriSmart.Daw;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.backend.AgriSmart.Entities.BuyerEntity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyerDaw {
    private String buyerId;
    private String buyerName;
    private String buyerEmail;
    private String buyerPassword;
    private String buyerContact;
    private String buyerAddress;
    private String buyerProfilePic;
    private Set<String> carts;

    private List<String> userRole = new ArrayList<>();

    // Create a copy constructer to initialize the BuyerDaw object with the BuyerEntity object
    public BuyerDaw(BuyerEntity buyer) {
        this.buyerId = buyer.getBuyerId();
        this.buyerName = buyer.getBuyerName();
        this.buyerEmail = buyer.getBuyerEmail();
        this.buyerPassword = buyer.getBuyerPassword();
        this.buyerContact = buyer.getBuyerContact();
        this.buyerAddress = buyer.getBuyerAddress();
        this.buyerProfilePic = buyer.getBuyerProfilePic();
        this.carts = buyer.getCarts();
    }
}
