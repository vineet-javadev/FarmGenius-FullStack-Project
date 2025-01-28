package com.backend.AgriSmart.ServiceImpl;

import java.util.List;

import com.backend.AgriSmart.Daw.ProductDaw;
import com.backend.AgriSmart.Daw.SellerDaw;

public interface SellerServicesInterface {
    
    public SellerDaw registerSeller(SellerDaw seller);

    public SellerDaw getSellerDetails(String id);

    public SellerDaw updateDetails(String id, SellerDaw sellerDaw);

    public boolean deleteAccount(String id);

    public ProductDaw addProduct(String id, ProductDaw productDaw);

    public boolean removeProduct(String id, String productId);

    public List<ProductDaw> getAllProducts(String id);

}
