package com.backend.AgriSmart.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.backend.AgriSmart.Daw.FarmerDaw;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "farmers")
public class FarmerEntity {
    @Id
    private String farmerId;

    private String farmerEmail;
    private String farmerPassword;

    private String farmerProfilePic;
    private String farmerName;
    private String farmerPhone;
    private String farmerAddress;
    private String farmerPincode;

    @JoinColumn(name = "farmerId")
    @OneToMany
    private List<CropEntity> farmerCrops = new ArrayList<>();

    @JoinColumn(name = "farmerId")
    @OneToMany
    private List<LandEntity> lands = new ArrayList<>();

    

    // create a copy constructior to convert FarmerEntity to FarmerDaw
    public FarmerEntity(FarmerDaw farmerDaw) {
        if (farmerDaw.getFarmerId() == null || farmerDaw.getFarmerId().isEmpty()) {
            this.farmerId = UUID.randomUUID().toString();
        } else {
            this.farmerId = farmerDaw.getFarmerId();
        }
        this.farmerEmail = farmerDaw.getFarmerEmail();
        this.farmerPassword = farmerDaw.getFarmerPassword();
        this.farmerProfilePic = farmerDaw.getFarmerProfilePic();
        this.farmerName = farmerDaw.getFarmerName();
        this.farmerPhone = farmerDaw.getFarmerPhone();
        this.farmerAddress = farmerDaw.getFarmerAddress();
        this.farmerPincode = farmerDaw.getFarmerPincode();
    }
}
