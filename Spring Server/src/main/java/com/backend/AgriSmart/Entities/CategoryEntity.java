package com.backend.AgriSmart.Entities;

import java.util.ArrayList;
import java.util.List;

import com.backend.AgriSmart.Daw.CategoryDaw;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
@Entity
public class CategoryEntity {
    @Id
    private String title;
    private List<String> products = new ArrayList<>();

    public CategoryEntity(CategoryDaw category) {
        this.title = category.getTitle();
        this.products = category.getProducts();
    }
}
