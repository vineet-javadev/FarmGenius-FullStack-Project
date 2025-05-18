package com.backend.AgriSmart.Controllers.Authenticated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.AgriSmart.Daw.CommentDaw;
import com.backend.AgriSmart.Services.CommentServices;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/comment")
public class CommentController {
    
    @Autowired
    private CommentServices commentServices;

    @PostMapping
    public ResponseEntity<CommentDaw> addNewComment(@RequestBody CommentDaw commentDaw){
        CommentDaw response = commentServices.addNewComment(commentDaw);
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<CommentDaw>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> removeComment(@PathVariable Integer commentId , @RequestParam String productId){
        if(commentServices.deleteComment(commentId , productId)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDaw> updateComment(@PathVariable Integer commentId , @RequestParam String content){
        CommentDaw response = commentServices.updateCommentContent(commentId, content);
        if (response==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response , HttpStatus.OK);
    }

}
