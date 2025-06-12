package com.backend.AgriSmart.Daw;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDaw {
    private String token;
    private List<String> role = new ArrayList<>();

}
