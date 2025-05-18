package com.backend.AgriSmart.Daw;

import java.util.List;

import com.backend.AgriSmart.Entities.CategoryEntity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDaw {
    public CategoryDaw(CategoryEntity response) {
        this.title = response.getTitle();
        this.products = response.getProducts();
    }
    private String title;
    private List<String> products;
}
