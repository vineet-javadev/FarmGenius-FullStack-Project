package com.backend.AgriSmart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.AgriSmart.Daw.ProductDaw;
import com.backend.AgriSmart.Entities.CategoryEntity;
import com.backend.AgriSmart.Entities.ProductEntity;
import com.backend.AgriSmart.Repositories.CategoryRepository;
import com.backend.AgriSmart.Repositories.ProductRepository;
import com.backend.AgriSmart.ServiceImpl.ProductServicesInterface;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServices implements ProductServicesInterface {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ProductDaw findById(String pId) {
        try {
            return new ProductDaw(productRepository.findById(pId).get());
        } catch (Exception e) {
            log.error("Something went wrong { ProductServices - findById} : {}", e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public ProductDaw createProduct(ProductDaw productDaw, String userId) {
        try {
            productDaw.setProductSellerId(userId);
            String category = productDaw.getProductCategory();
            CategoryEntity fromDB = categoryRepository.findById(category).get();
            ProductEntity response = productRepository.save(new ProductEntity(productDaw));
            fromDB.getProducts().add(response.getProductId());
            categoryRepository.save(fromDB);
            return new ProductDaw(response);
        } catch (Exception e) {
            log.error("Something went wrong { ProductServices - createProduct} : {}", e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public ProductDaw updateProduct(ProductDaw productDaw, String pId) {
        boolean isCategoryUpdate = false;
        try {
            ProductEntity productEntity = productRepository.findById(pId).get();
            if (productDaw.getProductName() != null || !productDaw.getProductName().isEmpty()) {
                productEntity.setProductName(productDaw.getProductName());
            }
            if (productDaw.getProductDescription() != null || !productDaw.getProductDescription().isEmpty()) {
                productEntity.setProductDescription(productDaw.getProductDescription());
            }
            if (productDaw.getProductPrice() != 0) {
                productEntity.setProductPrice(productDaw.getProductPrice());
            }
            if (productDaw.getProductCategory() != null || !productDaw.getProductCategory().isEmpty()) {
                isCategoryUpdate = true;
                CategoryEntity fromDB = categoryRepository.findById(productEntity.getProductCategory()).get();
                fromDB.getProducts().remove(productEntity.getProductId());
                categoryRepository.save(fromDB);
                productEntity.setProductCategory(productDaw.getProductCategory());
            }
            if(productDaw.getProductQuantity() !=0){
                productEntity.setProductQuantity(productDaw.getProductQuantity());
            }
            ProductEntity response = productRepository.save(productEntity);
            if(isCategoryUpdate){
                CategoryEntity fromDB = categoryRepository.findById(productDaw.getProductCategory()).get();
                fromDB.getProducts().add(response.getProductId());
                categoryRepository.save(fromDB);
            }
            return new ProductDaw(response);
        } catch (

        Exception e) {
            log.error("Something went wrong { ProductServices - updateProduct} : {}", e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public boolean deleteProduct(String pId){
        try {
            if(productRepository.existsById(pId)){
                ProductEntity productEntity = productRepository.findById(pId).get();
                CategoryEntity fromDB = categoryRepository.findById(productEntity.getProductCategory()).get();
                fromDB.getProducts().remove(productEntity.getProductId());
                categoryRepository.save(fromDB);
                productRepository.deleteById(pId);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("Something went wrong { ProductServices - deleteProduct} : {}", e.getMessage());
            return false;
        }
    }

}