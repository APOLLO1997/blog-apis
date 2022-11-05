package com.shikhar.blog.controllers;

import java.security.PublicKey;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shikhar.blog.payloads.ApiResponse;
import com.shikhar.blog.payloads.PostDto;
import com.shikhar.blog.payloads.PostResponse;
import com.shikhar.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
    // create post
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable("userId") Integer uid,@PathVariable("categoryId") Integer cid){
	  PostDto createPost = this.postService.createPost(postDto, uid, cid);	
	  return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
    }
    
	// get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber",defaultValue = "1",required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = "postId",required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
			){
		PostResponse posts = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(posts,HttpStatus.OK);
	}
	// get post by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable("userId") Integer uid){
		List<PostDto> postDtos= this.postService.getPostsByUser(uid);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	
	// get posts by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable("categoryId") Integer cid){
		List<PostDto> postDtos= this.postService.getPostByCategory(cid);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	
	// get post by if
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer pid){
		PostDto postDto= this.postService.getPostById(pid);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}

	//delete post
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable("postId") Integer pid){
		this.postService.deletePost(pid);
		return new ApiResponse("post deleted",true);
	}
	
	// update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable("postId") Integer pid) {
		PostDto updatedPost=this.postService.updatePost(postDto, pid);
		return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
	}
	
	//search by keyword
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByKeyword(@PathVariable("keyword") String kword){
		List<PostDto> postDtos = this.postService.searchPosts(kword);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}	
	
}
