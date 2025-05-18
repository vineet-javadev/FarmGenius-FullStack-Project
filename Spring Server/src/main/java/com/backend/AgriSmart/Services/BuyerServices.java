package com.backend.AgriSmart.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.AgriSmart.Daw.BuyerDaw;
import com.backend.AgriSmart.Daw.ProductDaw;
import com.backend.AgriSmart.Entities.BuyerEntity;
import com.backend.AgriSmart.Entities.UserEntity;
import com.backend.AgriSmart.Repositories.BuyerRepository;
import com.backend.AgriSmart.Repositories.UserRepository;
import com.backend.AgriSmart.ServiceImpl.BuyerServicesInterface;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BuyerServices implements BuyerServicesInterface {

    @Autowired
    private BuyerRepository buyerReopsitory;

    @Autowired
    private ProductServices productServices;

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder(12);

    @Override
    @Transactional
    public BuyerDaw registerBuyer(BuyerDaw buyer) {
        try {
            buyer.setBuyerPassword(passEncoder.encode(buyer.getBuyerPassword()));

            BuyerDaw response = new BuyerDaw(buyerReopsitory.save(new BuyerEntity(buyer)));
            // setup Users
            UserEntity user = new UserEntity(response.getBuyerId(), response.getBuyerName(), buyer.getBuyerPassword(),
                    response.getBuyerEmail(), Arrays.asList("USER", "BUYER"));
            userRepository.save(user);
            response.setBuyerPassword(null);
            return response;
        } catch (Exception e) {
            log.error("Somthing went Wrong {BuyerServices - registerBuyer function} : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public BuyerDaw getBuyer(String id) {
        try {
            BuyerEntity buyer = buyerReopsitory.findById(id).orElse(null);
            if (buyer != null) {
                buyer.setBuyerPassword(null);
                return new BuyerDaw(buyer);
            }
        } catch (Exception e) {
            log.error("Somthing went Wrong {BuyerServices - getBuyer function} : {}", e.getMessage());
        }
        return null;
    }

    @Override
    public BuyerDaw updateBuyer(BuyerDaw buyerDaw, String id) {
        try {
            BuyerEntity fromDB = buyerReopsitory.findById(id).get();

            if (fromDB == null)
                return null;

            if (buyerDaw.getBuyerName() != null || !buyerDaw.getBuyerName().isEmpty()) {
                fromDB.setBuyerName(buyerDaw.getBuyerName());
            }
            if (buyerDaw.getBuyerEmail() != null || !buyerDaw.getBuyerEmail().isEmpty()) {
                fromDB.setBuyerEmail(buyerDaw.getBuyerEmail());
            }
            if (buyerDaw.getBuyerAddress() != null || !buyerDaw.getBuyerAddress().isEmpty()) {
                fromDB.setBuyerAddress(buyerDaw.getBuyerAddress());
            }
            if (buyerDaw.getBuyerContact() != null || !buyerDaw.getBuyerContact().isEmpty()) {
                fromDB.setBuyerContact(buyerDaw.getBuyerContact());
            }
            if (buyerDaw.getBuyerProfilePic() != null || !buyerDaw.getBuyerProfilePic().isEmpty()) {
                fromDB.setBuyerProfilePic(buyerDaw.getBuyerProfilePic());
            }
            BuyerDaw response = new BuyerDaw(buyerReopsitory.save(fromDB));
            response.setBuyerPassword(null);
            return response;
        } catch (Exception e) {
            log.error("Somthing went Wrong {BuyerServices - updateBuyer function} : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteAccount(String id) {
        try {
            if (buyerReopsitory.existsById(id)) {
                buyerReopsitory.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("Somthing went Wrong {BuyerServices - deleteAccount function} : {}", e.getMessage());
            return false;
        }
    }

    @Override
    public List<ProductDaw> getProducts(String id) {
        try {
            BuyerEntity buyer = buyerReopsitory.findById(id).orElse(null);
            if (buyer == null)
                return null;

            List<ProductDaw> result = new ArrayList<>();
            for (String iterable_element : buyer.getCarts()) {
                ProductDaw temp = productServices.findById(iterable_element);
                result.add(temp);
            }
            return result;
        } catch (Exception e) {
            log.error("Somthing went Wrong {BuyerServices - getProducts function} : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public ProductDaw addProductIntoCart(String pId, String id) {
        try {
            BuyerEntity buyer = buyerReopsitory.findById(id).orElse(null);
            if (buyer == null)
                return null;

            if (!buyer.getCarts().contains(pId)) {
                buyer.getCarts().add(pId);
                buyerReopsitory.save(buyer);
                return productServices.findById(pId);
            }
            return null;
        } catch (Exception e) {
            log.error("Somthing went Wrong {BuyerServices - addProductIntoCart function} : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public boolean removeProductFromCart(String pId, String id) {
        try {
            BuyerEntity buyer = buyerReopsitory.findById(id).orElse(null);
            if (buyer == null)
                return false;

            if (buyer.getCarts().contains(pId)) {
                buyer.getCarts().remove(id);
                buyerReopsitory.save(buyer);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("Somthing went Wrong {BuyerServices - removeProductFromCart function} : {}", e.getMessage());
            return false;
        }
    }

}
