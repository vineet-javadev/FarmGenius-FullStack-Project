package com.backend.AgriSmart.ServiceImpl;

import java.util.List;

import com.backend.AgriSmart.Daw.CropDaw;
import com.backend.AgriSmart.Daw.FarmerDaw;
import com.backend.AgriSmart.Daw.LandDaw;

public interface FarmerServicesInterface {
    public FarmerDaw registerFarmer(FarmerDaw farmer);

    public FarmerDaw getFarmerById(String id);

    public List<FarmerDaw> getAllFarmers();

    public boolean deleteFarmer(String id);

    public FarmerDaw updateFarmer(FarmerDaw farmer, String id);

    public String getAddress(String id);

    public List<LandDaw> getFields(String id);

    public List<CropDaw> getCrops(String id);

    public List<CropDaw> addNewCropIntoList(String id, CropDaw crop);

    public Boolean removeACropFromList(String id, Integer crop);

    public CropDaw getCropDetails(Integer cropId);
    
}