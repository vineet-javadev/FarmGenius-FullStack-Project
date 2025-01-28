package com.backend.AgriSmart.ServiceImpl;

import com.backend.AgriSmart.Daw.ProductDaw;


public interface ProductServicesInterface {
    
    public ProductDaw findById(String pId);

    public ProductDaw createProduct(ProductDaw productDaw, String userId);

    public ProductDaw updateProduct(ProductDaw productDaw, String pId);

    public boolean deleteProduct(String pId);
}
