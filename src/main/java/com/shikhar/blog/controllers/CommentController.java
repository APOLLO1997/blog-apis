package com.shikhar.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shikhar.blog.payloads.CommentDto;
import com.shikhar.blog.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	@Autowired
	private CommentService commentService;
 @PostMapping("/post/{postId}/user/{userId}/comments")
 public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
		@PathVariable(name = "postId") Integer postId ,@PathVariable(name = "userId") Integer userId){
	CommentDto createdCommentDto= this.commentService.createComment(commentDto, postId,userId);
   return new ResponseEntity<CommentDto>(createdCommentDto,HttpStatus.CREATED);
 }
 
 
}
