package com.backend.AgriSmart.Daw;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.backend.AgriSmart.Entities.ProductEntity;
import com.backend.AgriSmart.Entities.SellerEntity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerDaw {
    private String sellerId;
    private String sellerName;
    private String sellerEmail;
    private String sellerPassword;
    private String sellerContact;
    private String sellerAddress;
    private String sellerProfilePic;
    private Set<ProductEntity> products;

    private List<String> userRole = new ArrayList<>();

    public SellerDaw(SellerEntity seller){
        this.sellerId = seller.getSellerId();
        this.sellerName = seller.getSellerName();
        this.sellerEmail = seller.getSellerEmail();
        this.sellerPassword = seller.getSellerPassword();
        this.sellerContact = seller.getSellerContact();
        this.sellerAddress = seller.getSellerAddress();
        this.sellerProfilePic = seller.getSellerProfilePic();
        this.setProducts(seller.getProducts() != null ? new HashSet<>(seller.getProducts()) : new HashSet<>());
    }
}
