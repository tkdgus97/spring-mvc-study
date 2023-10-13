package com.tkdgus.mvcstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class MvcstudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcstudyApplication.class, args);
	}

}
