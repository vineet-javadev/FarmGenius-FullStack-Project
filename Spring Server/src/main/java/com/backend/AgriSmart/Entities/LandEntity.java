package com.backend.AgriSmart.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

import com.backend.AgriSmart.Daw.LandDaw;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lands")
public class LandEntity {
    
    @Id
    private String landId;
    private String landArea;
    private String landSoilType;
    private String landIrrigationType;
    private String farmerId;


    public LandEntity(LandDaw landDaw) {
        if (landDaw.getLandId() == null || landDaw.getLandId().isEmpty()) {
            this.landId = UUID.randomUUID().toString();
        }else{
            this.landId = landDaw.getLandId();
        }
        this.landArea = landDaw.getLandArea();
        this.landSoilType = landDaw.getLandSoilType();
        this.landIrrigationType = landDaw.getLandIrrigationType();
        this.farmerId = landDaw.getFarmerId();
    }
}
