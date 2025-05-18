package com.backend.AgriSmart.Controllers.Authenticated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.backend.AgriSmart.Daw.CropDaw;
import com.backend.AgriSmart.Daw.FarmerDaw;
import com.backend.AgriSmart.Daw.LandDaw;
import com.backend.AgriSmart.Services.FarmerServices;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
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
            log.error("Farmer not found with id: " + farmerId);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllFarmers() {
        List<FarmerDaw> farmers = farmerServices.getAllFarmers();
        if (farmers != null) {
            return new ResponseEntity<>(farmers, HttpStatus.OK);
        } else {
            log.error("No farmers found");
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
    
    @GetMapping("location")
    public ResponseEntity<String> getAddress() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        String address = farmerServices.getAddress(id);
        if (address != null) {
            return new ResponseEntity<>(address, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
  
    @GetMapping("fields")
    public ResponseEntity<?> getFields(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        List<LandDaw> response = farmerServices.getFields(id);
        if (response != null) {
            return new ResponseEntity<>(response , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
   
    @GetMapping("crops")
    public ResponseEntity<List<CropDaw>> getCrops(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        List<CropDaw> response = farmerServices.getCrops(id);
        if (response != null) {
            return new ResponseEntity<>(response , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("addCrop")
    public ResponseEntity<List<CropDaw>> addNewCropIntoList(@RequestBody CropDaw crop) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        List<CropDaw> response = farmerServices.addNewCropIntoList(id, crop);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("removeCrop/{crop}")
    public ResponseEntity<Boolean> removeACropFromList(@PathVariable Integer crop){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        Boolean flag = farmerServices.removeACropFromList(id , crop);
        if(flag){
            return new ResponseEntity<>(flag , HttpStatus.OK);
        }else{
            return new ResponseEntity<>(flag , HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("getCrop/{crop}")
    public ResponseEntity<CropDaw> getACrop(@PathVariable Integer crop){
        CropDaw response = farmerServices.getCropDetails(crop);
        if(response != null){
            return new ResponseEntity<>(response , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
