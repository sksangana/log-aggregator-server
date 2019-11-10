package com.sangana.logserver.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sangana.logserver.data.LogText;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogAggregatorServiceTest {
	
    @Autowired
    LogAggregatorService logService;


	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void appendLogsTest() throws Exception {
		
		LogText log = new LogText();
		log.setFileName("log1.log");
		log.setHost("host1");
		log.setText("log text");
		
		boolean success = logService.appendLogs(log);
		
		assertTrue(success);
		
	}

}
