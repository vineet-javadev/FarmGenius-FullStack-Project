package com.backend.AgriSmart.Services;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.AgriSmart.Daw.CategoryDaw;
import com.backend.AgriSmart.Daw.ProductDaw;
import com.backend.AgriSmart.Entities.CategoryEntity;
import com.backend.AgriSmart.Repositories.CategoryRepository;
import com.backend.AgriSmart.ServiceImpl.CategoryServicesInterface;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryServices  implements CategoryServicesInterface {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductServices productServices;

    @Override
    public CategoryDaw addCategory(CategoryDaw category) {
        try {
            CategoryEntity response = categoryRepository.save(new CategoryEntity(category));
            return new CategoryDaw(response);

        } catch (Exception e) {
            log.error("Somthing went wrong { CategoryServices - addCategory Method } : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<ProductDaw> findProducts(String title){
        try {
            List<ProductDaw> result = new ArrayList<>();
            for (String iterable_element : categoryRepository.findById(title).get().getProducts()) {
                result.add(productServices.findById(iterable_element));
            }
            return result;
        } catch (Exception e) {
            log.error("Somthing went wrong { CategoryServices - findByTitle method } : {}", e.getMessage());
            return null;
        }
    }

}
