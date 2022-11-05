package com.shikhar.blog.services;

import com.shikhar.blog.payloads.CommentDto;

public interface CommentService {
 public CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId);
 public void deleteComment(Integer commentId);
 // update comment
}
