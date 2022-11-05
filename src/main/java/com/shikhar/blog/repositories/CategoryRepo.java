package com.shikhar.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shikhar.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
