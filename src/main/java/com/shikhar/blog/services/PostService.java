package com.shikhar.blog.services;

import java.util.List;
import com.shikhar.blog.payloads.PostDto;
import com.shikhar.blog.payloads.PostResponse;

public interface PostService {
 PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
 PostDto updatePost(PostDto postDto,Integer postId);
 void deletePost(Integer postId);
 PostResponse getAllPost(Integer pageInteger,Integer pageSize,String sortBy,String sortDir);
 PostDto getPostById(Integer postId);
 List<PostDto> getPostByCategory(Integer categoryId);
 List<PostDto> getPostsByUser(Integer userId);
 List<PostDto> searchPosts(String keyWord);
}
