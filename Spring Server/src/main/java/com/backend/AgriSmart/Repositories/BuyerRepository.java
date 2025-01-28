package com.backend.AgriSmart.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.AgriSmart.Entities.BuyerEntity;

@Repository
public interface BuyerRepository extends JpaRepository<BuyerEntity , String> {
    
    Optional<BuyerEntity> findByBuyerEmail(String username);
}
