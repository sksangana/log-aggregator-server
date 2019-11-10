package com.sangana.logserver.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sangana.logserver.data.LogDAO;
import com.sangana.logserver.data.LogText;

/**
 * @author sksangana
 * Service class that implements REST APIs for the Controller class
 */
@Service
@Transactional
public class LogAggregatorService {
	
	@Autowired
	LogDAO logDAO;

	public LogAggregatorService() {

	}
	
	/**
	 * @param log   Object that has information regarding the log data to write to the file
	 * @return true if successful else false
	 * @throws Exception Catches all the exception and throws a new Exception object 
	 */
	public boolean appendLogs(LogText log) throws Exception {
		
		boolean success = false;
		
		try {
			    // persist the log data
			//LogText txt = logDAO.save(log);
			//System.out.println("The Log Text inserted successfully in to the DB ..." + txt);

	        Charset utf8 = StandardCharsets.UTF_8;

	        String fileName = log.getHost() + "_" + log.getFileName();

	        BufferedWriter bufferdWriter = null;
	        FileWriter fileWriter = null;

	        try {
	        	fileWriter = new FileWriter(fileName, true);
	            bufferdWriter = new BufferedWriter(fileWriter);
	            bufferdWriter.write(log.getText());
	            bufferdWriter.write("\n");
	            success = true;
	        } catch (IOException e) {
	        	String msg = "ERROR :  Exception thrown while writing to teh file : " + fileName;
	        	System.out.println(msg + e.getCause());
	        	throw new Exception(msg, e.fillInStackTrace());
	        } finally {
	            try {
	                if (bufferdWriter != null)
	                	bufferdWriter.close();

	                if (fileWriter != null)
	                	fileWriter.close();
	            } catch (IOException ex) {
	            	System.out.println("ERROR : Cleaning up the resources ");	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
			String msg = "ERROR: Saving Log Text in to the DB ..." + e.getMessage();
			System.out.println("ERROR: Saving Log Text in to the DB ..." + e.getMessage());
			throw new Exception(msg, e.fillInStackTrace());
		}
		
		return success;
	}

}
