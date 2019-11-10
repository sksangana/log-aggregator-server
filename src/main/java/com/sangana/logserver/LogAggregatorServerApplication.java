package com.sangana.logserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author sksangana
 * This is the main class for the Log Aggregation Server application. This is a Springboot based  Maven application
 * This application exposes a REST API with POST Method to enable the log tailing agent client to invoke and 
 * transmit the log data in real time.  This application receives the log data and puts them in 
 * different files based on the client host/ip and log file name. 
 */
@SpringBootApplication
public class LogAggregatorServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogAggregatorServerApplication.class, args);
	}

}
