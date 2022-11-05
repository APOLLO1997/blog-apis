package com.shikhar.blog.services.impl;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Id;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shikhar.blog.entities.Category;
import com.shikhar.blog.entities.Post;
import com.shikhar.blog.entities.User;
import com.shikhar.blog.exceptions.ResourceNotFoundException;
import com.shikhar.blog.payloads.PostDto;
import com.shikhar.blog.payloads.PostResponse;
import com.shikhar.blog.repositories.CategoryRepo;
import com.shikhar.blog.repositories.PostRepo;
import com.shikhar.blog.repositories.UserRepo;
import com.shikhar.blog.services.PostService;

import net.bytebuddy.asm.Advice.This;
@Service
public class PostServiceImpl implements PostService {
    
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
    private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user= this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "user id", userId));
		Category cat= this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "category id", categoryId));
		Post post =this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(cat);
		Post createdPost= this.postRepo.save(post);
		return this.modelMapper.map(createdPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post= this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "post id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
	 Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "post id", postId));
     this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		Sort sort= (sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending());
        Pageable p =PageRequest.of(pageNumber, pageSize,sort);
        Page<Post> postPage=this.postRepo.findAll(p);
        List<Post> allPosts= postPage.getContent();
		List<PostDto> allPostsDtos = allPosts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(allPostsDtos);
		postResponse.setPageNumber(postPage.getNumber());
		postResponse.setPageSize(postPage.getSize());
		postResponse.setTotalElements(postPage.getTotalElements());
		postResponse.setTotalPages(postPage.getTotalPages());
		postResponse.setLastPage(postPage.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post= this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "post id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category cat= this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "category id", categoryId));
        List<Post> posts = this.postRepo.findByCategory(cat);
		List<PostDto> postDtos=posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	    return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user= this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "user id", userId));
        List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDtos=posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	    return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyWord) {
		List<Post> posts=this.postRepo.searchByContent("%"+keyWord+"%");
		List<PostDto> searchList=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return searchList;
		/*List<Post> allPosts= this.postRepo.findAll();
		List<Post> posts=new ArrayList<>();
		//String kwordString= ".* "+keyWord+" .*";
		for (Post post : allPosts) {
			String content = post.getContent();
			String[] spList=content.split(" ");
			for(String str: spList) {
				if(str.equals(keyWord)) {
					posts.add(post);
					break;
				}
			}
		}
		List<PostDto> postsContainingKeywordList= posts.stream().map((postobj)->this.modelMapper.map(postobj, PostDto.class)).collect(Collectors.toList());
		return postsContainingKeywordList;*/
	}

}
