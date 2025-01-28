package com.backend.AgriSmart.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.AgriSmart.Entities.SellerEntity;

@Repository
public interface SellerReopsitory extends JpaRepository<SellerEntity , String> {
     Optional<SellerEntity> findBySellerEmail(String username);
}
