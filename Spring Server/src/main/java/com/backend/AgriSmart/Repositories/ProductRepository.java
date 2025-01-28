package com.backend.AgriSmart.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.AgriSmart.Entities.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity , String> {
    
}
