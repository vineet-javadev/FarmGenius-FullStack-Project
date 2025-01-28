package com.backend.AgriSmart.Controllers.Authenticated;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.AgriSmart.Daw.ProductDaw;
import com.backend.AgriSmart.Services.CategoryServices;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CategoryServices categoryServices;

    @GetMapping("/{title}")
    public ResponseEntity<List<ProductDaw>> getAllProducts(@PathVariable String title) {
        List<ProductDaw> response = categoryServices.findProducts(title);
        if (response != null) {
            return new ResponseEntity<>(response , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
