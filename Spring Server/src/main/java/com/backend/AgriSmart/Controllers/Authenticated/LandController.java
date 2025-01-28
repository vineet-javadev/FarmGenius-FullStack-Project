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

import com.backend.AgriSmart.Daw.LandDaw;
import com.backend.AgriSmart.Services.LandServices;

@RestController
@RequestMapping("land")
public class LandController {

    @Autowired
    private LandServices landServices;
    
    @PostMapping
    public ResponseEntity<LandDaw> addLand(@RequestBody LandDaw landDetails){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String farmerId = authentication.getName();
        LandDaw landDaw = landServices.addLand(landDetails , farmerId);
        if(landDaw != null){
            return new ResponseEntity<>(landDaw, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{landId}")
    public ResponseEntity<LandDaw> getSingleLand(@PathVariable String landId) {
        LandDaw land = landServices.getLandById(landId);
        if (land != null) {
            return new ResponseEntity<>(land , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllLands() {
        return new ResponseEntity<>(landServices.getAllLands(), HttpStatus.OK);
    }

    @DeleteMapping("{landId}")
    public ResponseEntity<?> deleteLand(@PathVariable String landId) {
        if (landServices.deleteLand(landId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{landId}")
    public ResponseEntity<LandDaw> updateLand(@RequestBody LandDaw landDetails , @PathVariable String landId) {
        LandDaw updatedLand = landServices.updateLand(landDetails , landId);
        if (updatedLand != null) {
            return new ResponseEntity<>(updatedLand, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
}
