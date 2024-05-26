package com.example.demo.kafka.controller;


import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import com.example.demo.kafka.producer.KafkaTemplateProducerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class StringDataSender {

	private static final Logger logger = LoggerFactory.getLogger(StringDataSender.class);
	
	private KafkaTemplateProducerServer kafkaTemplateProducerServer;
	
	public StringDataSender(KafkaTemplateProducerServer kafkaTemplateProducerServer) {
		this.kafkaTemplateProducerServer = kafkaTemplateProducerServer;
	}	
	
	public void sendData() {
        logger.info("Sending data...");
        String uniqueKey = this.generateSessionId();
        
        String value="Hello this is testing at 5/26/2024 at 14:13pm form vadodara india";
        
        kafkaTemplateProducerServer.sendMessage(uniqueKey,value);
    }
	
	private String generateSessionId() {

		try {
			
		return RandomStringUtils.randomAlphanumeric(20);
		} catch (Exception e) {
		//LOGGER.error("Exception in generating random number: " + e.getMessage(), e);
		logger.info("Exception in generating random number: " + e.getMessage());
		throw e;
		}

		}
	
}
