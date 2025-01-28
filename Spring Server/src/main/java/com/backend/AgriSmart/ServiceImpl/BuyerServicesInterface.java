package com.backend.AgriSmart.ServiceImpl;

import java.util.List;

import com.backend.AgriSmart.Daw.BuyerDaw;
import com.backend.AgriSmart.Daw.ProductDaw;

public interface BuyerServicesInterface {
    
    public BuyerDaw registerBuyer(BuyerDaw buyer);

    public BuyerDaw getBuyer(String id);

    public BuyerDaw updateBuyer(BuyerDaw buyerDaw, String id);

    public boolean deleteAccount(String id);

    public List<ProductDaw> getProducts(String id);

    public ProductDaw addProductIntoCart(String pId, String id);

    public boolean removeProductFromCart(String pId, String id);
    
}
