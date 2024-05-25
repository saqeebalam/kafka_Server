package com.example.demo.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaProducerTest {
	public static void main(String[] args) {
		// Kafka producer configuration settings
		Properties props = new Properties();
		props.put("bootstrap.servers", "https://master-cicada-11420-us1-kafka.upstash.io:9092");
		props.put("sasl.mechanism", "SCRAM-SHA-256");
		props.put("security.protocol", "SASL_SSL");
		props.put("sasl.jaas.config",
				"org.apache.kafka.common.security.scram.ScramLoginModule required username=\"bWFzdGVyLWNpY2FkYS0xMTQyMCSfgAWi8szQKxnsK_ya5oHdG-Zt6iGPG9gbhrs\" password=\"NDgyZTViZDQtZGY3NC00ZDEzLTlhODItM2M1NTQ2Y2E5NTY5\";");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		// Create a new Kafka producer
		KafkaProducer<String, String> producer = new KafkaProducer<>(props);

		// Topic to which the message will be sent
		String topic = "saq04252024";

		// Message to be sent
		String key = "test052520240359";
		String value = "Hello, Kafka! from VADODARA test 3";

		// Create a producer record
		ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);

		// Send the record to Kafka asynchronously
		producer.send(record, new Callback() {
			@Override
			public void onCompletion(RecordMetadata metadata, Exception exception) {
				if (exception == null) {
					System.out.printf("Sent message with key: %s and value: %s to partition: %d with offset: %d%n", key,
							value, metadata.partition(), metadata.offset());
				} else {
					exception.printStackTrace();
				}
			}
		});

		// Close the producer
		producer.close();
	}
}
