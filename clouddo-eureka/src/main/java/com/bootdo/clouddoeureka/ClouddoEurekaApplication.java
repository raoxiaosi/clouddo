package com.bootdo.clouddoeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ClouddoEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClouddoEurekaApplication.class, args);
	}
}
