package com.showb.firstboot;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class FirstbootApplication {
	public static final String TIME_ZONE = "Asia/Seoul";


	@PostConstruct
	void started() { TimeZone.setDefault(TimeZone.getTimeZone(TIME_ZONE));}

	public static void main(String[] args) {
		SpringApplication.run(FirstbootApplication.class, args);
	}
}
