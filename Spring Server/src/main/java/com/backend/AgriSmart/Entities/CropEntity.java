package com.backend.AgriSmart.Entities;

import java.util.Date;

import com.backend.AgriSmart.Daw.CropDaw;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "crop")
public class CropEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cropId;
    private String cropName;
    private String landArea;
    private String photo;
    private Double price;
    private String description;
    private String farmerId;
    private Date createdDate = new Date();

    public CropEntity(CropDaw entity) {
        this.cropName = entity.getCropName();
        this.landArea = entity.getLandArea();
        this.photo = entity.getPhoto();
        this.price = entity.getPrice();
        this.description = entity.getDescription();
        this.farmerId = entity.getFarmerId();
    }
}