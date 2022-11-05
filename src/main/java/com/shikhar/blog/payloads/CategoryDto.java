package com.shikhar.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	private Integer categoryId;
	@NotEmpty
	@Size(min = 4,message = "min length 4")
	private String categoryTitle;
	@NotEmpty
	@Size(min = 40,message = "min length 40")
	private String categoryDescription;
}
