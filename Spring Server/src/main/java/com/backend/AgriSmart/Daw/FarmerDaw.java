package com.backend.AgriSmart.Daw;

import java.util.List;
import java.util.ArrayList;

import com.backend.AgriSmart.Entities.CropEntity;
import com.backend.AgriSmart.Entities.FarmerEntity;
import com.backend.AgriSmart.Entities.LandEntity;


import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmerDaw {
    private String farmerId;

    private String farmerEmail;
    private String farmerPassword;

    private String farmerProfilePic;
    private String farmerName;
    private String farmerContact;
    private String farmerAddress;
    private String farmerPincode;

    private List<String> userRole = new ArrayList<>();

    private List<CropEntity> farmerCrops;

    private List<LandEntity> lands;

    public FarmerDaw(FarmerEntity farmerEntity) {
        this.farmerId = farmerEntity.getFarmerId();
        this.farmerEmail = farmerEntity.getFarmerEmail();
        this.farmerPassword = farmerEntity.getFarmerPassword();
        this.farmerProfilePic = farmerEntity.getFarmerProfilePic();
        this.farmerName = farmerEntity.getFarmerName();
        this.farmerContact = farmerEntity.getFarmerContact();
        this.farmerAddress = farmerEntity.getFarmerAddress();
        this.farmerPincode = farmerEntity.getFarmerPincode();

        this.farmerCrops = farmerEntity.getFarmerCrops() != null ? new ArrayList<>(farmerEntity.getFarmerCrops()) : new ArrayList<>();
        this.lands = farmerEntity.getLands() != null ? new ArrayList<>(farmerEntity.getLands()) : new ArrayList<>();
        
    }
}
