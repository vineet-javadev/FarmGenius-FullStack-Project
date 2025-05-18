package com.backend.AgriSmart.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.AgriSmart.Daw.FarmerDaw;
import com.backend.AgriSmart.Daw.LandDaw;
import com.backend.AgriSmart.Entities.LandEntity;
import com.backend.AgriSmart.Repositories.LandReopsitory;
import com.backend.AgriSmart.ServiceImpl.LandServicesInterface;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LandServices implements LandServicesInterface {

    @Autowired
    private LandReopsitory landReopsitory;

    @Autowired
    private FarmerServices farmerServices;

    @Override
    @Transactional
    public LandDaw addLand(LandDaw land, String farmerId) {
        try {
            LandEntity newLand = new LandEntity(land);
            FarmerDaw farmer = farmerServices.getFarmerById(farmerId);
            if (farmer == null) {
                return null;
            }
            newLand.setFarmerId(farmerId);
            LandEntity created = landReopsitory.save(newLand);

            // Add land to farmer
            farmer.getLands().add(created);
            farmerServices.updateFarmer(farmer, farmerId);
            return new LandDaw(created);

        } catch (Exception e) {
            log.error("Somthing went Wrong {LandSErvices - addLand method} : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public LandDaw getLandById(String id) {
        try {
            if (landReopsitory.existsById(id)) {
                return new LandDaw(landReopsitory.findById(id).get());
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Somthing went Wrong {LandSErvices - getLandById method} : {}", e.getMessage());
            return null;
        }
            
    }

    @Override
    public List<LandDaw> getAllLands() {
        try {
            return landReopsitory.findAll().stream().map(LandDaw::new).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Somthing went Wrong {LandSErvices - getAllLands method} : {}", e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public boolean deleteLand(String landId) {
        try {
            if (landReopsitory.existsById(landId)) {
                String farmerId = landReopsitory.findById(landId).get().getFarmerId();
                FarmerDaw farmer = farmerServices.getFarmerById(farmerId);
                farmer.getLands().removeIf(land -> land.getLandId().equals(landId));
                farmerServices.updateFarmer(farmer, farmerId);
                landReopsitory.deleteById(landId);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.error("Somthing went Wrong {LandSErvices - deleteLand method} : {}", e.getMessage());
            return false;
        }
    }

    @Override
    public LandDaw updateLand(LandDaw land, String id) {
        try {
            if (landReopsitory.existsById(id)) {
                LandEntity landEntity = landReopsitory.findById(id).get();
                if (land.getLandArea() != null) {
                    landEntity.setLandArea(land.getLandArea());
                } else {
                    landEntity.setLandArea(landEntity.getLandArea());
                }
                if (land.getLandSoilType() != null) {
                    landEntity.setLandSoilType(land.getLandSoilType());
                } else {
                    landEntity.setLandSoilType(landEntity.getLandSoilType());
                }
                if (land.getLandIrrigationType() != null) {
                    landEntity.setLandIrrigationType(land.getLandIrrigationType());
                } else {
                    landEntity.setLandIrrigationType(landEntity.getLandIrrigationType());
                }

                return new LandDaw(landReopsitory.save(landEntity));
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Somthing went Wrong {LandSErvices - updateLand method} : {}", e.getMessage());
            return null;
        }
    }

}
