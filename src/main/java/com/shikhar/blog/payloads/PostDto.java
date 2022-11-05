package com.shikhar.blog.payloads;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
 @Id	
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Integer id;
 private String title;
 private String content;
 private String imageName;
 private Date addedDate;
 private CategoryDto category;
 private UserDto user;
 private Set<CommentDto> comments=new HashSet<>();
}