package com.backend.AgriSmart.Daw;

import java.util.List;

import com.backend.AgriSmart.Entities.CommentEntity;
import com.backend.AgriSmart.Entities.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDaw {
    private String productId;
    private String productName;
    private String productCategory;
    private String productDescription;
    private Double productPrice;
    private Long productQuantity;
    private List<String> productImage; 
    private String productSellerId;  
    private List<CommentEntity> comments;

    public ProductDaw(ProductEntity productEntity) {
       this.productId = productEntity.getProductId();
       this.productName = productEntity.getProductName();
       this.productCategory = productEntity.getProductCategory();
       this.productDescription = productEntity.getProductDescription();
       this.productPrice = productEntity.getProductPrice();
       this.productQuantity = productEntity.getProductQuantity();
       this.productImage = productEntity.getProductImage();
       this.productSellerId = productEntity.getProductSellerId();
       this.comments = productEntity.getComments();
    }
}
