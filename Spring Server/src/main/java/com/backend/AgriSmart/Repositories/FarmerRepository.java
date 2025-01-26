package com.backend.AgriSmart.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.AgriSmart.Entities.FarmerEntity;

@Repository
public interface FarmerRepository extends JpaRepository<FarmerEntity, String> {

    Optional<FarmerEntity> findByFarmerEmail(String username);
}
