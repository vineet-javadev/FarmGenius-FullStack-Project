package com.backend.AgriSmart.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.AgriSmart.Entities.LandEntity;

@Repository
public interface LandReopsitory extends JpaRepository<LandEntity, String> {

    public LandEntity findByFarmerId(String id);
}
