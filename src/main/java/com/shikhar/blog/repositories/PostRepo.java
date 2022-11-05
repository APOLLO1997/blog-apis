package com.shikhar.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shikhar.blog.entities.Category;
import com.shikhar.blog.entities.Post;
import com.shikhar.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	 List<Post> findByCategory(Category category);
	 List<Post> findByUser(User user);
	 List<Post> findByTitleContaining(String title);
	 List<Post> findByContentContaining(String content);
	 
	 @Query("select p from Post p where p.content like :key")
	 List<Post> searchByContent(@Param("key") String content);
}
