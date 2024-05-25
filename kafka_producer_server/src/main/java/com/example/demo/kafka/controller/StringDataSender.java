package com.example.demo.kafka.controller;


import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import com.example.demo.kafka.producer.KafkaProducerServer;


@Component
public class StringDataSender {

	
	private KafkaProducerServer kafkaProducerServer;
	
	public StringDataSender(KafkaProducerServer kafkaProducerServer) {
		this.kafkaProducerServer = kafkaProducerServer;
	}	
	
	public void sendData() {
        System.out.println("Sending data...");
        String uniqueKey = this.generateSessionId();
        
        String value="Hello this is testing at 5/25/2024 at 8:12pm form vadodara india";
        
        kafkaProducerServer.sendMessage(uniqueKey,value);
    }
	
	private String generateSessionId() {

		try {
			
		return RandomStringUtils.randomAlphanumeric(20);
		} catch (Exception e) {
		//LOGGER.error("Exception in generating random number: " + e.getMessage(), e);
		System.out.println("Exception in generating random number: " + e.getMessage());
		throw e;
		}

		}
	
}
