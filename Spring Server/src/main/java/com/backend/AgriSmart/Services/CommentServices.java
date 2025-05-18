package com.backend.AgriSmart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.AgriSmart.Daw.CommentDaw;
import com.backend.AgriSmart.Entities.CommentEntity;
import com.backend.AgriSmart.Entities.ProductEntity;
import com.backend.AgriSmart.Repositories.CommentRepository;
import com.backend.AgriSmart.Repositories.ProductRepository;
import com.backend.AgriSmart.ServiceImpl.CommentServicesInterface;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentServices implements CommentServicesInterface {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public CommentDaw updateCommentContent(Integer id, String content) {
        try {
            CommentEntity fromDB = commentRepository.findById(id).get();
            if (fromDB == null)
                throw new RuntimeException("Comment not found");

            fromDB.setContent(content);
            CommentEntity save = commentRepository.save(fromDB);
            return new CommentDaw(save);

        } catch (Exception e) {
            log.error(
                    "Something is wrong in CommentServices file in updateCommentContent method\n\n" + e.getMessage() + "\n");
            return null;
        }
    }

    @Override
    @Transactional
    public CommentDaw addNewComment(CommentDaw comment) {
        try {
            CommentEntity respose = new CommentEntity();
            respose = commentRepository.save(new CommentEntity(comment));
            ProductEntity product = productRepository.findById(comment.getProductId()).get();

            if (product == null) {
                throw new RuntimeException("Product not Found");
            }

            product.getComments().add(respose);

            productRepository.save(product);

            product.getComments().add(respose);
            productRepository.save(product);

            return new CommentDaw(respose);
        } catch (Exception e) {
            log.error("Something is wrong in CommentServices file in addNewComment method\n\n" + e.getMessage() + "\n");
            return null;
        }
    }

    @Override
    @Transactional
    public boolean deleteComment(Integer CommentId, String ProductId) {
        try {
            if (commentRepository.existsById(CommentId)) {
                commentRepository.deleteById(CommentId);
                ProductEntity productFromDB = productRepository.findById(ProductId).get();
                if (productFromDB == null) {
                    throw new RuntimeException("Product not Found");
                }
                productFromDB.getComments().removeIf(t -> t.getId().equals(CommentId));
                productRepository.save(productFromDB);
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("Something is wrong in CommentServices file in deleteComment method\n\n" + e.getMessage() + "\n");
            return false;
        }
    }

}
