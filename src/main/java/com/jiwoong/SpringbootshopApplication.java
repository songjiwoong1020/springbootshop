package com.jiwoong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})//db와 시큐리티는 만지기 전까진 사용하지 않게 둠.
public class SpringbootshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootshopApplication.class, args);
	}

}
