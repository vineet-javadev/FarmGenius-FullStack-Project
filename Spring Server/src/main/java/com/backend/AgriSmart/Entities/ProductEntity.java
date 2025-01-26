package com.backend.AgriSmart.Entities;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import com.backend.AgriSmart.Daw.ProductDaw;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data   
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@Entity
public class ProductEntity {
    @Id
    private String productId;
    private String productName;
    private String productCategory;
    private String productDescription;
    private Double productPrice;
    private Long productQuantity;
    private List<String> productImage; 
    private String productSellerId;
    @JoinColumn(name = "productId")
    @OneToMany
    private List<CommentEntity> comments = new ArrayList<>();

    public ProductEntity(ProductDaw productDaw){
        if(productDaw.getProductId() == null || productDaw.getProductId().isEmpty()){
            this.productId = UUID.randomUUID().toString();
        }else{
            this.productId = productDaw.getProductId();
        }
        this.productName = productDaw.getProductName();
        this.productCategory = productDaw.getProductCategory();
        this.productDescription = productDaw.getProductDescription();
        this.productPrice = productDaw.getProductPrice();
        this.productQuantity = productDaw.getProductQuantity();
        this.productImage = productDaw.getProductImage();
        this.productSellerId = productDaw.getProductSellerId();
        if(productDaw.getComments() != null || !productDaw.getComments().isEmpty()){
            this.comments = productDaw.getComments();
        }
    }
}
