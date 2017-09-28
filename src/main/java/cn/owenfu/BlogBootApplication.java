package cn.owenfu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BlogBootApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BlogBootApplication.class, args);
	}
}
