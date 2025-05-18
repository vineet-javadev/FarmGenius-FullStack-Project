package com.backend.AgriSmart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.AgriSmart.Entities.UserEntity;
import com.backend.AgriSmart.Repositories.UserRepository;
import com.backend.AgriSmart.ServiceImpl.UserServiceInterface;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class  UserServices implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtServices jwtServices;

    private BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder(12);

    @Override
    public String forgotPassword(String id , String password) {
        try {
            UserEntity user = userRepository.findByUserId(id).get();
            if (user != null) {
                
                user.setUserPassword(passEncoder.encode(password));
                userRepository.save(user);
                String token = jwtServices.generateToken(id);
                return token;
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("Error in forgotPassword : {} " , e.getMessage());
            return null;
        }
    }

}