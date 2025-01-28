package com.backend.AgriSmart.ServiceImpl;

import java.util.List;

import com.backend.AgriSmart.Daw.FarmerDaw;

public interface FarmerServicesInterface {
    public FarmerDaw registerFarmer(FarmerDaw farmer);

    public FarmerDaw getFarmerById(String id);

    public List<FarmerDaw> getAllFarmers();

    public boolean deleteFarmer(String id);

    public FarmerDaw updateFarmer(FarmerDaw farmer, String id);
}