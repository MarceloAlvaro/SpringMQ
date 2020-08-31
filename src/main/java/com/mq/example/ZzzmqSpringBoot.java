package com.mq.example;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.mq.spring.boot.MQConfigurationProperties;

@SpringBootApplication
@RestController
@Component
//@RequestMapping("/send")
//@EnableJms
//@Configuration
public class ZzzmqSpringBoot {
	public static final long RECEIVE_TIMEOUT_NO_WAIT = -1;
	public static final String QUEUE = "DEV.QUEUE.1";

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@GetMapping("MQCloud/Put")
	String send(){
		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		int i = 1;
    	String zzzMsg = new String();
		try{
	    	while (i < 11) {
//		    	System.out.println("MQCloud/Put - Antes " + i);
		        zzzMsg = "Hello MQ Cloud! Msg: " + i + " - " +  ts;
		        jmsTemplate.convertAndSend(QUEUE, zzzMsg);
		    	System.out.println("MQCloud/Put - Depois OK - "  + i + " - " + ts);
		        i++;
			}
	        return "MQCloud/Put OK";
	    }catch(JmsException ex){
	        ex.printStackTrace();
	    	System.out.println("MQCloud/Put - Depois NOK - " + i + " - " + ts);
	        return "MQCloud/Put NOK";
	    }
	}
	
	@GetMapping("MQCloud/Get")
	String recv(){
		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		int i = 1;
    	String zzzMsg = new String();
	    try{
	    	while (i < 11) {
//		    	System.out.println("MQCloud/Get - Antes " + i + " - ");
		    	jmsTemplate.setReceiveTimeout(JmsTemplate.RECEIVE_TIMEOUT_INDEFINITE_WAIT);
		    	zzzMsg = jmsTemplate.receiveAndConvert(QUEUE).toString();
		        System.out.println("MQCloud/Get - Depois OK - "  + i + " - "+ ts);
		        i++;
			}
		}catch(JmsException ex){
	        ex.printStackTrace();
	    	System.out.println("MQCloud/Get - Depois NOK - "  + ts);
	        return "MQCloud/Get - Depois NOK - "  + ts;
	    }
	    return "MQCloud/Get - OK - msg: "+zzzMsg + " - "  + ts;
		
	}
	
//	@Autowired
//    private JmsTemplate jmsTemplate;
//
//	@GetMapping("send")
//	String send(){
//    	System.out.println("ZZZ001");
//    	try{
//	    	System.out.println("ZZZ002");
//	        jmsTemplate.convertAndSend("DEV.QUEUE.1", "Hello World!");
//	    	System.out.println("ZZZ003");
//	        return "OK";
//	    }catch(JmsException ex){
//	    	System.out.println("ZZZ004");
//	        ex.printStackTrace();
//	        return "FAIL";
//	    }
//	}
//	
//	@GetMapping("recv")
//	String recv(){
//	    try{
//	        return jmsTemplate.receiveAndConvert("DEV.QUEUE.1").toString();
//	    }catch(JmsException ex){
//	        ex.printStackTrace();
//	        return "FAIL";
//	    }
//	}
//	@Primary
//	@Bean
//	MQConfigurationProperties mqConfigurationProperties() {
//	 System.setProperty('javax.net.ssl.keyStore', '/path/to/keystore.jks')
//	 System.setProperty('javax.net.ssl.keyStorePassword', 'XXXXXXX')

//	String ZqueueManager = "ZZZQM";
//	String Zchannel = "CLOUD.ADMIN.SVRCONN";
//	String ZconnName = "zzzqm-510a.qm.us-south.mq.appdomain.cloud(30907)";
//	String Zuser = "alexandre";
//	String Zpassword = "o1pVkgFCf7DxiDjQDlZZdAej4f6fPwWszCOJ0Zb5ZI20";
//	boolean ZuserAuthenticationMQCSP = true;
//
//	
//	MQConfigurationProperties zMQ = new MQConfigurationProperties();
//	zMQ.setQueueManager("ZZZQM");
//	zMQ.setChannel("CLOUD.ADMIN.SVRCONN");
//	zMQ.setConnName("zzzqm-510a.qm.us-south.mq.appdomain.cloud(30907)");
//	zMQ.setUser("alexandre");
//	zMQ.setPassword("o1pVkgFCf7DxiDjQDlZZdAej4f6fPwWszCOJ0Zb5ZI20");
//	return zMQ;
	              
	   // If the provided SSL cipher suite begins with "SSL", 
	   // replace it with "TLS" instead.
	   // SSL_* is IBM JRE CipherSuite name.
	   // TLS_* is Oracle JRE CipherSuite name. 
//	   sslCipherSuite: 'TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256',
	              
	   // true - if using IBM JRE
	   // false - if using non-IBM JRE, ex: Oracle, OpenJDK, etc
//	   useIBMCipherMappings: false
	 
//	}
}

