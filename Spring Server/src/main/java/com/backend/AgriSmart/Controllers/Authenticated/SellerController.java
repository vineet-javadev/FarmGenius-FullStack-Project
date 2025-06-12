package com.backend.AgriSmart.Controllers.Authenticated;

import java.util.List;

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
import com.backend.AgriSmart.Daw.SellerDaw;
import com.backend.AgriSmart.Services.SellerServices;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerServices sellerServices;

    // APIs for Seller

    @GetMapping
    public ResponseEntity<SellerDaw> getDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        SellerDaw seller = sellerServices.getSellerDetails(id);
        if (seller != null) {
            seller.setUserRole(authentication.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .toList());
            return new ResponseEntity<>(seller, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PutMapping
    public ResponseEntity<SellerDaw> updateDetails(@RequestBody SellerDaw sellerDaw) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        SellerDaw response = sellerServices.updateDetails(id, sellerDaw);
        if (response != null)
            return new ResponseEntity<>(response, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        if (sellerServices.deleteAccount(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // APIs for seller's Product

    @PostMapping
    public ResponseEntity<ProductDaw> addProduct(@RequestBody ProductDaw productDaw) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        ProductDaw response = sellerServices.addProduct(id, productDaw);
        if (response != null)
            return new ResponseEntity<>(response, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);

    }

    @DeleteMapping("{productId}")
    public ResponseEntity<?> removeProduct(@PathVariable String productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        if (sellerServices.removeProduct(id, productId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/all-products")
    public ResponseEntity<List<ProductDaw>> getAllProducts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        List<ProductDaw> response = sellerServices.getAllProducts(id);
        if (response != null)
            return new ResponseEntity<>(response, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
