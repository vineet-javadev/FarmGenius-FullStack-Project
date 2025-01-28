package com.backend.AgriSmart.ServiceImpl;

import com.backend.AgriSmart.Daw.CommentDaw;

public interface CommentServicesInterface {
    
    public CommentDaw updateCommentContent(Integer id, String content);

    public CommentDaw addNewComment(CommentDaw comment);
    
    public boolean deleteComment(Integer CommentId, String ProductId);
    
}
