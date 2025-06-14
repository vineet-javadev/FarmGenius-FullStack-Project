package com.backend.AgriSmart.Controllers.Public;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.backend.AgriSmart.Daw.BuyerDaw;
import com.backend.AgriSmart.Daw.FarmerDaw;
import com.backend.AgriSmart.Daw.LoginResponseDaw;
import com.backend.AgriSmart.Daw.SellerDaw;
import com.backend.AgriSmart.Daw.UserDaw;
import com.backend.AgriSmart.Repositories.UserRepository;
import com.backend.AgriSmart.Services.BuyerServices;
import com.backend.AgriSmart.Services.FarmerServices;
import com.backend.AgriSmart.Services.JwtServices;
import com.backend.AgriSmart.Services.SellerServices;
import com.backend.AgriSmart.Services.UserDetailsServiceLayer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private FarmerServices farmerServices;

    @Autowired
    private SellerServices sellerServices;

    @Autowired
    private BuyerServices buyerServices;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceLayer userDetailsServiceLayer;

    @Autowired
    JwtServices jwtServices;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/health")
    public String healthCheck() {
        return "Server is Live...";
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDaw> login(@RequestBody UserDaw userDaw){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDaw.getUserEmail(), userDaw.getUserPassword()));
            UserDetails user = userDetailsServiceLayer.loadUserByUsername(userDaw.getUserEmail());
            String token = jwtServices.generateToken(user.getUsername());
            LoginResponseDaw loginResponse = new LoginResponseDaw(token, user.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .toList());
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } catch (Exception e) {
            log.warn("Login failed: {}", e.getMessage());
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register-farmer")
    public ResponseEntity<FarmerDaw> registerFarmer(@RequestBody FarmerDaw farmer) {
        FarmerDaw registeredFarmer = farmerServices.registerFarmer(farmer);
        if (registeredFarmer != null) {
            return new ResponseEntity<>(registeredFarmer, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/register-seller")
    public ResponseEntity<SellerDaw> registerSeller(@RequestBody SellerDaw seller) {
        SellerDaw response = sellerServices.registerSeller(seller);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
    }
    
    @PostMapping("/register-buyer")
    public ResponseEntity<BuyerDaw> registerBuyer(@RequestBody BuyerDaw buyer) {
        BuyerDaw response = buyerServices.registerBuyer(buyer);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
    }   

}
