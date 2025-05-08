package com.backend.AgriSmart.Daw;

import java.util.Date;

import com.backend.AgriSmart.Entities.CropEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CropDaw {
    private int cropId;
    private String cropName;
    private String landArea;
    private String photo;
    private Double price;
    private String description;
    private String farmerId;
    private Date createdDate;

    public CropDaw(CropEntity entity) {
        this.cropId = entity.getCropId();
        this.cropName = entity.getCropName();
        this.landArea = entity.getLandArea();
        this.photo = entity.getPhoto();
        this.price = entity.getPrice();
        this.createdDate = entity.getCreatedDate();
        this.description = entity.getDescription();
    }
}
