package com.backend.AgriSmart.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.AgriSmart.Entities.CategoryEntity;
// import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {

    // List<CategoryEntity> findByTitle(String title);
}
