package com.shikhar.blog;

import org.hibernate.dialect.identity.CockroachDB1920IdentityColumnSupport;
import org.junit.jupiter.api.Test;
import org.springframework.aop.target.SimpleBeanTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shikhar.blog.repositories.UserRepo;

@SpringBootTest
class BlogAppApisApplicationTests {

	@Autowired
	//private UserRepo userRepo;
	@Test
	void contextLoads() {
	}
    /*@Test
	public void repoTest() {
	  String classname = this.userRepo.getClass().getName();
	  String packagename = this.userRepo.getClass().getPackageName();
	  System.out.println(classname);
	  System.out.println(packagename); 
	}*/
}
