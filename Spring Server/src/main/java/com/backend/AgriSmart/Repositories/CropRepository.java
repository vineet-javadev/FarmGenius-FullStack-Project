package com.backend.AgriSmart.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.AgriSmart.Entities.CropEntity;

@Repository
public interface CropRepository extends JpaRepository<CropEntity, Integer> {
    @Query(value = "SELECT * FROM CropEntity WHERE cropName = :cropName AND farmerId = :farmerId", nativeQuery = true)
    List<CropEntity> findByCropNameAndFarmerId(@Param("cropName") String cropName, @Param("farmerId") String farmerId);

}
