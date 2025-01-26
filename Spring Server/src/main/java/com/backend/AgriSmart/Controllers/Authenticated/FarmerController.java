package com.backend.AgriSmart.Controllers.Authenticated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.AgriSmart.Daw.FarmerDaw;
import com.backend.AgriSmart.Services.FarmerServices;

import java.util.List;

@RestController
@RequestMapping("/farmer")
public class FarmerController {

    @Autowired
    private FarmerServices farmerServices;

    @GetMapping
    public ResponseEntity<FarmerDaw> getSingleFarmer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String farmerId = authentication.getName();
        
        FarmerDaw farmer = farmerServices.getFarmerById(farmerId);
        if (farmer != null) {
            return new ResponseEntity<>(farmer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllFarmers() {
        List<FarmerDaw> farmers = farmerServices.getAllFarmers();
        if (farmers != null) {
            return new ResponseEntity<>(farmers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFarmer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        if (farmerServices.deleteFarmer(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<FarmerDaw> updateFarmer(@RequestBody FarmerDaw farmer) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        FarmerDaw updatedFarmer = farmerServices.updateFarmer(farmer, id);
        if (updatedFarmer != null) {
            return new ResponseEntity<>(updatedFarmer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
