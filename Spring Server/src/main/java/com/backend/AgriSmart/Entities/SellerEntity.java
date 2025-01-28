package com.backend.AgriSmart.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.backend.AgriSmart.Daw.SellerDaw;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sellers")
@Entity
public class SellerEntity {
    @Id
    private String sellerId;
    private String sellerName;
    private String sellerEmail;
    private String sellerPassword;
    private String sellerContact;
    private String sellerAddress;
    private String sellerProfilePic;
    @JoinColumn(name = "productSellerId")
    @OneToMany
    private Set<ProductEntity> products = new HashSet<>();

    public SellerEntity(SellerDaw seller) {
        if(seller.getSellerId() == null || seller.getSellerId().isEmpty()){
            this.sellerId = UUID.randomUUID().toString();
        }else{
            this.sellerId = seller.getSellerId();
        }
        this.sellerName = seller.getSellerName();
        this.sellerEmail = seller.getSellerEmail();
        this.sellerPassword = seller.getSellerPassword();
        this.sellerContact = seller.getSellerContact();
        this.sellerProfilePic = seller.getSellerProfilePic();
        this.sellerAddress = seller.getSellerAddress();
    }
}
