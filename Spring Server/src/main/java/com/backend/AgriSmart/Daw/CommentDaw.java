package com.backend.AgriSmart.Daw;

import java.util.Date;

import com.backend.AgriSmart.Entities.CommentEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDaw {
    private Integer id;
    private String content;
    private String userId;
    private String userName;
    private String productId;
    private Date createdDate;

    public CommentDaw(CommentEntity entity){
        this.id = entity.getId();
        this.content = entity.getContent();
        this.userId = entity.getUserId();
        this.userName = entity.getUserName();
        this.productId = entity.getProductId();
        this.createdDate = entity.getCreatedDate();
    }
}
