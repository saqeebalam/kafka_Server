package com.example.demo.kafka.producer;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;


@Service
public class KafkaProducerServer {

	@Value("${kafka.topic.name}")
	private String kafkaTopicName;
	private KafkaTemplate<String, String> kafkaTemplete;
	
	
	//Below constructor is use for constructor dependency injection for KafkaTemplate
		public KafkaProducerServer(KafkaTemplate<String, String> kafkaTemplete) {
			this.kafkaTemplete = kafkaTemplete;
		}
	
			
		
		public void sendMessage(String key,String message)	
		{
			//LOGGER.info(String.format("Message sent %s", message));
			System.out.println(String.format("topic name %s",kafkaTopicName));
			System.out.println(String.format("key sent %s value sent %s",key,message));
			CompletableFuture<SendResult<String, String>> send = kafkaTemplete.send(kafkaTopicName,key,message);
			
		}
}
