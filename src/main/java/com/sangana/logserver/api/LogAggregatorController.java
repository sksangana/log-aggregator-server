package com.sangana.logserver.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sangana.logserver.data.LogText;
import com.sangana.logserver.service.LogAggregatorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author sksangana
 * This class is the REST controller which Exposes POST API 
 *
 */

@RestController
@RequestMapping("/")
public class LogAggregatorController {

	@Autowired
	LogAggregatorService logService;
	

	 @PostMapping("/logs/append")
	 @ApiOperation(
	           value = "Sends the log data to the server"
	   )
	   @ApiResponses({
	           @ApiResponse(code = 201, message = "Data sent successfully"),
	           @ApiResponse(code = 400, message = "Empty or Invalid Request Body "),
	           @ApiResponse(code = 500, message = "Could not serve request. One or more system components are down")
	   })
	public ResponseEntity<String> appendLogs(@RequestBody LogText text) throws Exception {
		
		System.out.println("In the method appendLogs with the data : " + text);
		try {
			boolean success = logService.appendLogs(text);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			System.out.println("ERROR : Exception thrown while writing the log data to the file " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
