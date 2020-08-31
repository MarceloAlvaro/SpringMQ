package com.mq.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableJms
public class ZzzmqSpringBootApplication {

	@Autowired
    private static JmsTemplate jmsTemplate;

	public static void main(String[] args) {
		SpringApplication.run(ZzzmqSpringBootApplication.class, args);
	}

}