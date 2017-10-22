package com.baciu;

import java.util.Collections;

import javax.servlet.SessionTrackingMode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EntityScan
@SpringBootApplication
public class RatePlace {
	
	@Bean
	ServletContextInitializer servletContextInitializer() {
	    // keep session only in cookie so that jsessionid is not appended to URL.
	    return servletContext -> servletContext.setSessionTrackingModes(
	            Collections.singleton(SessionTrackingMode.COOKIE));
	}

	public static void main(String[] args) {
		SpringApplication.run(RatePlace.class);
	}
}
