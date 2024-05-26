package com.example.demo.kafka.payload;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class KafkaMessageMetadata {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@Column(columnDefinition = "TEXT")
    private String message;

    @Column(name = "`offset`")
    private long offset;

    @Column(name = "`partition`")
    private int partition;

    @Column(name = "`timestamp`")
    private long timestamp;
    private String topicname;  
    
    public KafkaMessageMetadata() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public int getPartition() {
		return partition;
	}

	public void setPartition(int partition) {
		this.partition = partition;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getTopicname() {
		return topicname;
	}

	public void setTopicname(String topicname) {
		this.topicname = topicname;
	}

	public KafkaMessageMetadata( String message, long offset, int partition, long timestamp, String topicname) {
		super();
		this.message = message;
		this.offset = offset;
		this.partition = partition;
		this.timestamp = timestamp;
		this.topicname = topicname;
	}

    
   
}