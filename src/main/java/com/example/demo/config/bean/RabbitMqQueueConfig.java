package com.example.demo.config.bean;

import org.springframework.amqp.core.Queue;


public class RabbitMqQueueConfig {
	
    public Queue Queue() {
        return new Queue("hello");
    }


}
