package com.backend.AgriSmart.Entities;

import java.util.Set;
import java.util.HashSet;
import java.util.UUID;

import com.backend.AgriSmart.Daw.BuyerDaw;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "buyers")
@Entity
public class BuyerEntity {
    @Id
    private String buyerId;
    private String buyerName;
    private String buyerEmail;
    private String buyerPassword;
    private String buyerContact;
    private String buyerAddress;
    private String buyerProfilePic;
    // Here we add products id in carts list
    private Set<String> carts = new HashSet<>();

    public BuyerEntity(BuyerDaw buyerDaw){
        if(buyerDaw.getBuyerId() == null || buyerDaw.getBuyerId().isEmpty()){
            this.buyerId = UUID.randomUUID().toString();
        }else{
            this.buyerId = buyerDaw.getBuyerId();
        }
        this.buyerName = buyerDaw.getBuyerName();
        this.buyerEmail = buyerDaw.getBuyerEmail();
        this.buyerPassword = buyerDaw.getBuyerPassword();
        this.buyerContact = buyerDaw.getBuyerContact();
        this.buyerAddress = buyerDaw.getBuyerAddress();
        this.buyerProfilePic = buyerDaw.getBuyerProfilePic();
    }
}
