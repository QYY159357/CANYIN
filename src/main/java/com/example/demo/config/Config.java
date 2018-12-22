package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = { "classpath:bean.properties" })
@ImportResource(value = { "classpath:spring/spring*.xml" })
public class Config {

}
