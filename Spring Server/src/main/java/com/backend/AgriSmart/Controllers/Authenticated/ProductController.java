package com.backend.AgriSmart.Controllers.Authenticated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.AgriSmart.Daw.ProductDaw;
import com.backend.AgriSmart.Services.ProductServices;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @GetMapping("{pId}")
    public ResponseEntity<ProductDaw> getProductDetails(@PathVariable String pId) {
        ProductDaw response = productServices.findById(pId);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public void getAllProducts() {
        // get all products
    }

    @PostMapping
    public ResponseEntity<ProductDaw> addProduct(@RequestBody ProductDaw product) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        ProductDaw response = productServices.createProduct(product, id);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PutMapping("/{pId}")
    public ResponseEntity<ProductDaw> updateProduct(@RequestBody ProductDaw product, @PathVariable String pId) {
        ProductDaw response = productServices.updateProduct(product, pId);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("{pId}")
    public ResponseEntity<?> deleteProduct(@PathVariable String pId) {
        if (productServices.deleteProduct(pId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
