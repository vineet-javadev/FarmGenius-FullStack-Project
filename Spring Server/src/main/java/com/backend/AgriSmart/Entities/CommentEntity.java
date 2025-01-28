package com.backend.AgriSmart.Entities;

import java.util.Date;

import com.backend.AgriSmart.Daw.CommentDaw;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    private String userId;
    private String userName;
    private String productId;
    private Date createdDate = new Date();

    public CommentEntity(CommentDaw commentDaw){
        this.id = commentDaw.getId();
        this.content = commentDaw.getContent();
        this.userId = commentDaw.getUserId();
        this.userName = commentDaw.getUserName();
        this.productId = commentDaw.getProductId();
        if(commentDaw.getCreatedDate() != null){
            this.createdDate = commentDaw.getCreatedDate();
        }
    }
}
