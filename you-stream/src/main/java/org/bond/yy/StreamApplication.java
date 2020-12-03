package org.bond.yy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@SpringBootApplication
@EnableBinding(Sink.class)
public class StreamApplication {
	private static Logger logger = LoggerFactory.getLogger(StreamApplication.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(StreamApplication.class, args); 
	}

//	@StreamListener(Sink.INPUT)
//	public void receive(Person payload) {
//		logger.info("Received: " + payload);
//	}
//	
	@StreamListener(Sink.INPUT)
	public void receive(Message payload) {
		logger.info("Received: " + (Person)payload.getPayload());
	}
	
	public static class Person {
		private String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String toString() {
			return this.name;
		}
	}
}
