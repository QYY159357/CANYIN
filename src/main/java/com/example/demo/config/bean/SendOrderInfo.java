package com.example.demo.config.bean;

import java.util.Map;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendOrderInfo {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send(Map<String,String> i) {
		this.rabbitTemplate.convertAndSend("09A981106F70B6E6C8450AFC6EB6BCB0", i);
	}
	
}
