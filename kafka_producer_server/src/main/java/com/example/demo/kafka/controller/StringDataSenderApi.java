package com.example.demo.kafka.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.kafka.producer.KafkaTemplateProducerServer;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/kafka")
public class StringDataSenderApi {

	private static final Logger logger = LoggerFactory.getLogger(StringDataSenderApi.class);

	@Value("${kafka.topic.name}")
	private String kafkaTopicName;

	private KafkaTemplateProducerServer kafkaTemplateProducerServer;

	public StringDataSenderApi(KafkaTemplateProducerServer kafkaTemplateProducerServer) {
		this.kafkaTemplateProducerServer = kafkaTemplateProducerServer;
	}

	// http://localhost:8080/api/v1/kafka/publish
	@GetMapping("/publish")
	public ResponseEntity<String> publish(@RequestParam("message") String message) {
		String key = this.generateSessionId();
		kafkaTemplateProducerServer.sendMessage(key, message);

		String responceMessage = "Message [" + message + "] with key[" + key + "] sent to the topic [" + kafkaTopicName
				+ "]";

		return ResponseEntity.ok(responceMessage);
	}

	private String generateSessionId() {

		try {

			return RandomStringUtils.randomAlphanumeric(20);
		} catch (Exception e) {
			// LOGGER.error("Exception in generating random number: " + e.getMessage(), e);
			logger.info("Exception in generating random number: " + e.getMessage());
			throw e;
		}

	}

}
