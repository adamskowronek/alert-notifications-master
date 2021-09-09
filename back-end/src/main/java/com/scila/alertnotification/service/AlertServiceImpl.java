package com.scila.alertnotification.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scila.alertnotification.dao.AlertDAO;
import com.scila.alertnotification.entity.Alert;

@Service
public class AlertServiceImpl implements AlertService {

	AlertDAO alertDAO;
	
	@Autowired
	public AlertServiceImpl(AlertDAO alertDAO_) {
		alertDAO = alertDAO_;
	}
	
	@Override
	@Transactional
	public List<Alert> findAll() {
		return alertDAO.findAll();
	}
	
	@Transactional
	public List<Alert> findMostRecent() {
		return alertDAO.findMostRecent();
	}

	@Override
	public Alert findByAlertId(String alertId) {
		return alertDAO.findByAlertId(alertId);
	}
	
	@Override
	@Transactional
	public boolean verifyUser(String username, String password) {
		return alertDAO.verifyUser(username, password);
	}

	@Override
	public void addToken(String token) {
		alertDAO.addToken(token);
	}
}
