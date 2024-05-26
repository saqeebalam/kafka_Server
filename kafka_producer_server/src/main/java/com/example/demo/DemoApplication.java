package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.kafka.controller.StringDataSender;

@SpringBootApplication
public class DemoApplication {

	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        logger.info("Application started successfully.");
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            StringDataSender stringDataSender = ctx.getBean(StringDataSender.class);
            logger.info("Calling sendData method");
            stringDataSender.sendData();
        };
    }
}
