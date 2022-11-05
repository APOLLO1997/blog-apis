package com.shikhar.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shikhar.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

}
