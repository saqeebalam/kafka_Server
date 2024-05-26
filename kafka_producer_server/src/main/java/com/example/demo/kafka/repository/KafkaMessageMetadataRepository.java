package com.example.demo.kafka.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.kafka.payload.KafkaMessageMetadata;

@Repository
public interface KafkaMessageMetadataRepository extends JpaRepository<KafkaMessageMetadata, Long> {
}
