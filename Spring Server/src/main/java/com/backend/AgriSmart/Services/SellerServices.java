package com.backend.AgriSmart.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.AgriSmart.Daw.ProductDaw;
import com.backend.AgriSmart.Daw.SellerDaw;
import com.backend.AgriSmart.Entities.ProductEntity;
import com.backend.AgriSmart.Entities.SellerEntity;
import com.backend.AgriSmart.Entities.UserEntity;
import com.backend.AgriSmart.Repositories.SellerReopsitory;
import com.backend.AgriSmart.Repositories.UserRepository;
import com.backend.AgriSmart.ServiceImpl.SellerServicesInterface;

import jakarta.transaction.Transactional;

@Service
public class SellerServices implements SellerServicesInterface {

    @Autowired
    private SellerReopsitory sellerReopsitory;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductServices productServices;

    private BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder(12);

    @Override
    @Transactional
    public SellerDaw registerSeller(SellerDaw seller) {
        try {
            seller.setSellerPassword(passEncoder.encode(seller.getSellerPassword()));
            SellerEntity sellerEntity = new SellerEntity(seller);
            SellerDaw response = new SellerDaw(sellerReopsitory.save(sellerEntity));

            // setup Users
            UserEntity user = new UserEntity(response.getSellerId(), response.getSellerName(),
                    seller.getSellerPassword(), response.getSellerEmail(), Arrays.asList("USER", "SELLER"));
            userRepository.save(user);
            response.setSellerPassword(null);
            return response;
        } catch (Exception e) {
            System.err.print("Somthing went Wrong {SellerServices - registerSeller method}");
            return null;
        }
    }

    @Override
    public SellerDaw getSellerDetails(String id) {
        try {
            SellerDaw response = new SellerDaw(sellerReopsitory.findById(id).get());
            response.setSellerPassword(null);
            return response;
        } catch (Exception e) {
            System.err.print("Somthing went Wrong {SellerServices - getSellerDetails method}");
            return null;
        }
    }

    @Override
    public SellerDaw updateDetails(String id, SellerDaw sellerDaw) {
        try {
            SellerEntity fromDB = sellerReopsitory.findById(id).get();

            if (fromDB == null)
                return null;

            if (sellerDaw.getSellerName() != null || !sellerDaw.getSellerName().isEmpty()) {
                fromDB.setSellerName(sellerDaw.getSellerName());
            }
            if (sellerDaw.getSellerEmail() != null || !sellerDaw.getSellerEmail().isEmpty()) {
                fromDB.setSellerEmail(sellerDaw.getSellerEmail());
            }
            if (sellerDaw.getSellerAddress() != null || !sellerDaw.getSellerAddress().isEmpty()) {
                fromDB.setSellerAddress(sellerDaw.getSellerAddress());
            }
            if (sellerDaw.getSellerContact() != null || !sellerDaw.getSellerContact().isEmpty()) {
                fromDB.setSellerContact(sellerDaw.getSellerContact());
            }
            if( sellerDaw.getSellerProfilePic() != null || !sellerDaw.getSellerProfilePic().isEmpty()){
                fromDB.setSellerProfilePic(sellerDaw.getSellerProfilePic());
            }
            SellerDaw response = new SellerDaw(sellerReopsitory.save(fromDB));
            response.setSellerPassword(null);
            return response;
        } catch (Exception e) {
            System.err.print("Somthing went Wrong {SellerServices - updateDetails method}");
            return null;
        }
    }

    @Override
    public boolean deleteAccount(String id) {
        if (sellerReopsitory.existsById(id)) {
            try {
                sellerReopsitory.deleteById(id);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public ProductDaw addProduct(String id, ProductDaw productDaw) {
        try {
            SellerEntity fromDB = sellerReopsitory.findById(id).get();
            if (fromDB == null)
                return null;

            ProductDaw newProduct = productServices.createProduct(productDaw, id);
            fromDB.getProducts().add(new ProductEntity(newProduct));
            sellerReopsitory.save(fromDB);
            return newProduct;
        } catch (Exception e) {
            System.err.print("Somthing went Wrong {SellerServices - addProduct method}");
            return null;
        }
    }

    @Override
    @Transactional
    public boolean removeProduct(String id, String productId) {
        try {
            SellerEntity fromDB = sellerReopsitory.findById(id).get();
            if (fromDB == null)
                return false;
            if (fromDB.getProducts().removeIf(product -> product.getProductId().equals(productId))
                    && productServices.deleteProduct(productId)) {
                sellerReopsitory.save(fromDB);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.print("Somthing went Wrong {SellerServices - removeProduct method}");
            return false;
        }
    }

    @Override
    public List<ProductDaw> getAllProducts(String id) {
        try {
            List<ProductDaw> result = new ArrayList<>();
            SellerEntity fromDB = sellerReopsitory.findById(id).get();
            for (ProductEntity entity : fromDB.getProducts()) {
                result.add(new ProductDaw(entity));
            }
            return result;

        } catch (Exception e) {
            System.err.print("Somthing went Wrong {SellerServices - getAllProducts method}");
            return null;
        }
    }

}
