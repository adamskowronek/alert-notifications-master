package com.scila.alertnotification.dao;

import java.math.BigInteger;
import java.util.List;

import com.scila.alertnotification.entity.Alert;

/**
 * This interface is used to communicate with the Db (querying).
 */
public interface AlertDAO {

	/**
	 * Queries database for all entries.
	 * @return list of all entry objects.
	 */
	public List<Alert> findAll();
	
	/**
	 * Queries database for specific alert.
	 * @param alertId the alert to be searched for.
	 * @return requested alert.
	 */
	public Alert findByAlertId(String alertId);
	
	/**
	 * Finds most recent entries within an interval specified in the
	 * application.properties file.
	 * @return list of most recent entries.
	 */
	public List<Alert> findMostRecent();
	
	/**
	 * Queries database for user.
	 * @return true if user exists.
	 */
	public boolean verifyUser(String username, String password);
	
	public void addToken(String token);
}
