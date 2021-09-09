package com.scila.alertnotification.service;

import java.math.BigInteger;
import java.util.List;

import com.scila.alertnotification.entity.Alert;

/**
 * This interface is used for logic layer of the application.
 * The methods call corresponding methods from the db layer.
 */
public interface AlertService {

	public List<Alert> findAll();
		
	public Alert findByAlertId(String alertId);
	
	public List<Alert> findMostRecent();
	
	public boolean verifyUser(String username, String password);
	
	public void addToken(String token);
}
