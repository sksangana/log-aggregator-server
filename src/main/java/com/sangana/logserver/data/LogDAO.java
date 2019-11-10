package com.sangana.logserver.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sangana.logserver.data.LogText;

@Repository
public interface LogDAO extends CrudRepository<LogText, String> {

	
}
