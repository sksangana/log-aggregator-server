package com.sangana.logserver.api;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.net.URI;

import com.sangana.logserver.data.LogText;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class LogAggregatorControllerTest {
	

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @LocalServerPort
    int randomServerPort;
    
    @Autowired
    LogAggregatorController logController;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void appenLogsTest() throws Exception {
		LogText log = new LogText();
		log.setFileName("log1.log");
		log.setHost("host1");
		log.setText("log text");
		
		String url = "http://localhost:" + randomServerPort + "/api/logs/append";
		URI uri = new URI(url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");      
 
        HttpEntity<LogText> request = new HttpEntity<>(log, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

}
