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

import com.backend.AgriSmart.Daw.BuyerDaw;
import com.backend.AgriSmart.Daw.ProductDaw;
import com.backend.AgriSmart.Services.BuyerServices;

@RestController
@RequestMapping("/buyer")
public class BuyerController {

    @Autowired
    private BuyerServices buyerServices;

    // APIs for Buyer owny6ui

    @GetMapping
    public ResponseEntity<BuyerDaw> getDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        BuyerDaw buyerDaw = buyerServices.getBuyer(id);
        if (buyerDaw != null)
            return new ResponseEntity<>(buyerDaw, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<BuyerDaw> updateDetails(@RequestBody BuyerDaw buyerDaw) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        BuyerDaw response = buyerServices.updateBuyer(buyerDaw, id);
        if (response != null)
            return new ResponseEntity<>(response, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        if (buyerServices.deleteAccount(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    // APIs for Buyer's Products

    @GetMapping("/cart-itemlist")
    public ResponseEntity<?> getAllProducts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        List<ProductDaw> productDaws = buyerServices.getProducts(id);
        if (productDaws != null)
            return new ResponseEntity<>(productDaws, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{pId}")
    public ResponseEntity<ProductDaw> addProductIntoCart(@PathVariable String pId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        ProductDaw productDaw = buyerServices.addProductIntoCart(pId, id);
        if (productDaw != null)
            return new ResponseEntity<>(productDaw , HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("{pId}")
    public ResponseEntity<?> deleteProductFromCart(@PathVariable String pId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        if (buyerServices.removeProductFromCart(pId, id))
            return new ResponseEntity<>( HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
