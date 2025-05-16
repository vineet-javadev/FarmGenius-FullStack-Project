package com.backend.AgriSmart.Controllers.Authenticated;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.AgriSmart.Daw.ProductDaw;
import com.backend.AgriSmart.Services.CategoryServices;
import com.backend.AgriSmart.Services.UserServices;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CategoryServices categoryServices;

    @Autowired
    private UserServices userServices;

    @GetMapping("/{title}")
    public ResponseEntity<List<ProductDaw>> getAllProducts(@PathVariable String title) {
        List<ProductDaw> response = categoryServices.findProducts(title);
        if (response != null) {
            return new ResponseEntity<>(response , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping()
    public ResponseEntity<String> forgotPassword(@RequestBody String password) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        String token = userServices.forgotPassword(id, password);
        if(token != null){
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
