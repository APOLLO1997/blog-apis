package com.shikhar.blog.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.shikhar.blog.payloads.CommentDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
public class Post {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer postId;
 @Column(name = "post_title",length = 100, nullable = false)
 private String title;
 @Column(name = "post_content",length=1000, nullable = false)
 private String content;
 @Column(name = "post_imageNameURL")
 private String imageName;
 @Column(name = "post_addedDate",nullable = false)
 private Date addedDate;
 
 @ManyToOne
 @JoinColumn(name = "category_id")
 private Category category;
 @ManyToOne
 private User user;
 @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
 private Set<Comment> comments=new HashSet<>();
}
