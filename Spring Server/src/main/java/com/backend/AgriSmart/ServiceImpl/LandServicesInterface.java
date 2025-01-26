package com.backend.AgriSmart.ServiceImpl;

import java.util.List;

import com.backend.AgriSmart.Daw.LandDaw;

public interface LandServicesInterface {
    public LandDaw addLand(LandDaw land, String farmerId);

    public LandDaw getLandById(String id);

    public List<LandDaw> getAllLands();

    public boolean deleteLand(String id);

    public LandDaw updateLand(LandDaw land, String id);
}
