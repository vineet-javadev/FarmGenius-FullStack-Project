package com.backend.AgriSmart.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.AgriSmart.Entities.CommentEntity;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity , Integer> {
    
}
