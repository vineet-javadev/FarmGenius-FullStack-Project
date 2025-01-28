package com.backend.AgriSmart.Daw;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDaw {
    private Long id;
    private String userId;
    private String username;
    private String userPassword;
    private String userEmail;
    private List<String> userRole;
}
