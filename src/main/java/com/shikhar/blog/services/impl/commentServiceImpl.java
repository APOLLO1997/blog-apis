package com.shikhar.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shikhar.blog.entities.Comment;
import com.shikhar.blog.entities.Post;
import com.shikhar.blog.entities.User;
import com.shikhar.blog.exceptions.ResourceNotFoundException;
import com.shikhar.blog.payloads.CommentDto;
import com.shikhar.blog.repositories.CommentRepo;
import com.shikhar.blog.repositories.PostRepo;
import com.shikhar.blog.repositories.UserRepo;
import com.shikhar.blog.services.CommentService;
@Service
public class commentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId,Integer userId) {
		User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "user id", userId));
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "post id", postId));
		Comment comment= this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		Comment savedComment=this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment=this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "comment id", commentId));
        this.commentRepo.delete(comment);
	}

}
