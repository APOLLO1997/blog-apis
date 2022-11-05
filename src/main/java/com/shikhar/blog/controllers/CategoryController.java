package com.shikhar.blog.controllers;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.shikhar.blog.payloads.ApiResponse;
import com.shikhar.blog.payloads.CategoryDto;
import com.shikhar.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
 @Autowired
 private CategoryService categoryService;
 // create
 @PostMapping("/")
 public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
	CategoryDto createCategoryDto = this.categoryService.createCategory(categoryDto);
	return new ResponseEntity<CategoryDto>(createCategoryDto,HttpStatus.CREATED);
 }
 // update
 @PutMapping("/{catId}")
 public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("catId") Integer cid) {
	CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, cid);
	return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
 }
 // delete
 @DeleteMapping("/{catId}")
 public ResponseEntity<ApiResponse> deleteCategory(@Valid @PathVariable("catId") Integer cid){
	 this.categoryService.deleteCategory(cid);
	 return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted", true),HttpStatus.OK);
 }
 // get
 @GetMapping("/{catId}")
 public ResponseEntity<CategoryDto> getcategoryById(@PathVariable("catId") Integer catId){
	 return ResponseEntity.ok(this.categoryService.getCategory(catId));
 }
 // get all
 @GetMapping("/")
 public ResponseEntity<List<CategoryDto>> getcategories(){
	 return ResponseEntity.ok(this.categoryService.getAllCategories());
 }
}
