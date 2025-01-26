package com.backend.AgriSmart.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.AgriSmart.Entities.LandEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LandReopsitory extends JpaRepository<LandEntity, String> {

    public LandEntity findByFarmerId(String id);
}
