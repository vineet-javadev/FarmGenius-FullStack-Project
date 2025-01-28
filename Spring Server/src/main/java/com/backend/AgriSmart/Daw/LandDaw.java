package com.backend.AgriSmart.Daw;

import lombok.Data;

import com.backend.AgriSmart.Entities.LandEntity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LandDaw {
    private String landId;
    private String landArea;
    private String landSoilType;
    private String landIrrigationType;
    private String farmerId;

    // create a copy constructor to copy LandEntity to LandDaw
    public LandDaw(LandEntity landEntity) {
        this.landId = landEntity.getLandId();
        this.landArea = landEntity.getLandArea();
        this.landSoilType = landEntity.getLandSoilType();
        this.landIrrigationType = landEntity.getLandIrrigationType();
        this.farmerId = landEntity.getFarmerId();
    }
}
