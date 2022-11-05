package com.shikhar.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shikhar.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
