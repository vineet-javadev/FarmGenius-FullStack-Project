package com.backend.AgriSmart.ServiceImpl;

import java.util.List;

import com.backend.AgriSmart.Daw.CategoryDaw;
import com.backend.AgriSmart.Daw.ProductDaw;

public interface CategoryServicesInterface {
    
    public CategoryDaw addCategory(CategoryDaw category);

    public List<ProductDaw> findProducts(String title);

}
