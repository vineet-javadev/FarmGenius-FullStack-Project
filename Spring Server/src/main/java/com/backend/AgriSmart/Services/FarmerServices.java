package com.backend.AgriSmart.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.AgriSmart.Daw.FarmerDaw;
import com.backend.AgriSmart.Entities.FarmerEntity;
import com.backend.AgriSmart.Entities.LandEntity;
import com.backend.AgriSmart.Entities.UserEntity;
import com.backend.AgriSmart.Repositories.FarmerRepository;
import com.backend.AgriSmart.Repositories.UserRepository;
import com.backend.AgriSmart.ServiceImpl.FarmerServicesInterface;

@Service
public class FarmerServices implements FarmerServicesInterface {

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private LandServices landServices;

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder(12);

    @Override
    @Transactional
    public FarmerDaw registerFarmer(FarmerDaw farmer) {
        try {
            FarmerEntity temp = new FarmerEntity(farmer);
            temp.setFarmerPassword(passEncoder.encode(farmer.getFarmerPassword()));
            if (farmer.getFarmerCrops() != null) {
                temp.setFarmerCrops(new ArrayList<>(farmer.getFarmerCrops()));
            } else {
                temp.setFarmerCrops(null);
            }
            FarmerDaw response =  new FarmerDaw(farmerRepository.save(temp));

            // assign password to null to password security
            response.setFarmerPassword(null);
            // setup Users
            UserEntity user = new UserEntity(response.getFarmerId(), response.getFarmerName(), temp.getFarmerPassword(),
                    response.getFarmerEmail(),  Arrays.asList("USER", "FARMER"));
            userRepository.save(user);

            return response;
        } catch (Exception e) {
            System.err.print("Somthing went Wrong {FarmerServices - registerFarmer method}");
            return null;
        }
    }

    @Override
    public FarmerDaw getFarmerById(String id) {
        try {
            FarmerDaw response = new FarmerDaw(farmerRepository.findById(id).get());
            response.setFarmerPassword(null);
            return response;
        } catch (Exception e) {
            System.err.print("Somthing went Wrong {FarmerServices - getFarmerById method}");
            return null;
        }
    }

    @Override
    public List<FarmerDaw> getAllFarmers() {
        try {
            List<FarmerDaw> response = new ArrayList<>();
            for (FarmerEntity farmer : farmerRepository.findAll()) {
                FarmerDaw temp = new FarmerDaw(farmer);
                temp.setFarmerPassword(null);
                response.add(temp);
            }
            return response;

        } catch (Exception e) {
            System.err.print("Somthing went Wrong {FarmerServices - getAllFarmer method}");
            return null;
        }
    }

    @Override
    @Transactional
    public boolean deleteFarmer(String id) {
        try {
            FarmerEntity farmer = farmerRepository.findById(id).get();
            List<LandEntity> lands = farmer.getLands();
            for (LandEntity land : lands) {
                landServices.deleteLand(land.getLandId());
            }
            farmerRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.err.print("Somthing went Wrong {FarmerServices - deleteFarmer method}");
            return false;
        }
    }

    @Override
    public FarmerDaw updateFarmer(FarmerDaw farmer, String id) {
        try {
            if (farmerRepository.findById(id).isPresent()) {
                FarmerEntity temp = farmerRepository.findById(id).get();
                if (farmer.getFarmerName() != null) {
                    temp.setFarmerName(farmer.getFarmerName());
                } else {
                    temp.setFarmerName(temp.getFarmerName());
                }
                if (farmer.getFarmerEmail() != null) {
                    temp.setFarmerEmail(farmer.getFarmerEmail());
                } else {
                    temp.setFarmerEmail(temp.getFarmerEmail());
                }
                if (farmer.getFarmerPhone() != null) {
                    temp.setFarmerPhone(farmer.getFarmerPhone());
                } else {
                    temp.setFarmerPhone(temp.getFarmerPhone());
                }
                if (farmer.getFarmerAddress() != null) {
                    temp.setFarmerAddress(farmer.getFarmerAddress());
                } else {
                    temp.setFarmerAddress(temp.getFarmerAddress());
                }
                if (farmer.getFarmerCrops() != null) {
                    temp.setFarmerCrops(new ArrayList<>(farmer.getFarmerCrops()));
                } else {
                    temp.setFarmerCrops(temp.getFarmerCrops());
                }
                if (farmer.getLands() != null) {
                    temp.setLands(farmer.getLands());
                } else {
                    temp.setLands(temp.getLands());
                }
                FarmerDaw tempResponse = new FarmerDaw(farmerRepository.save(temp));
                tempResponse.setFarmerPassword(null);
                return tempResponse;
            }
            return null;
        } catch (Exception e) {
            System.err.print("Somthing went Wrong {FarmerServices - UpdateFarmer method}");
            return null;
        }
    }

}
