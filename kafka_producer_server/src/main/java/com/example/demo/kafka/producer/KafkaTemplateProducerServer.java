package com.example.demo.kafka.producer;

import org.springframework.stereotype.Service;

import com.example.demo.kafka.payload.KafkaMessageMetadata;
import com.example.demo.kafka.repository.KafkaMessageMetadataRepository;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class KafkaTemplateProducerServer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaTemplateProducerServer.class);
	
	@Value("${kafka.topic.name}")
	private String kafkaTopicName;
	private KafkaTemplate<String, String> kafkaTemplete;
	private final KafkaMessageMetadataRepository metadataRepository;
	
	//Below constructor is use for constructor dependency injection for KafkaTemplate
		public KafkaTemplateProducerServer(KafkaTemplate<String, String> kafkaTemplete , KafkaMessageMetadataRepository metadataRepository ) {
			this.metadataRepository = metadataRepository;
			this.kafkaTemplete = kafkaTemplete;
		}
	
			 
		/*
		 * This is for sending key and value
		 */
//		public void sendMessage(String key,String message)	
//		{
//			//LOGGER.info(String.format("Message sent %s", message));
//			System.out.println(String.format("topic name %s",kafkaTopicName));
//			System.out.println(String.format("key sent %s value sent %s",key,message));
//			CompletableFuture<SendResult<String, String>> send = kafkaTemplete.send(kafkaTopicName,key,message);
//			
//		}
		
		
		public void sendMessage(String key, String message) {

			CompletableFuture<SendResult<String, String>> future = kafkaTemplete.send(kafkaTopicName, key, message);

			future.whenComplete((result, ex) -> {
				if (ex == null) {
					
					RecordMetadata metadata = result.getRecordMetadata();
					
					KafkaMessageMetadata messageMetadata = new KafkaMessageMetadata(message, metadata.offset(),
							metadata.partition(), metadata.timestamp(), kafkaTopicName);
					
					metadataRepository.save(messageMetadata);
					
					logger.info("Sent message=[" + message + "] with offset=[" + metadata.offset()
							+ "], partition=[" + metadata.partition() + "], timestamp=[" + metadata.timestamp() + "]");
					
				} else {
					logger.error("Unable to send message=[" + message + "] due to : " + ex.getMessage());
				}
			});

		}
		
}
