package com.scila.alertnotification.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.scila.alertnotification.entity.*;
import com.scila.alertnotification.notification.MobileNotification;

@Repository
public class AlertDAOImpl implements AlertDAO {
	
	private EntityManager entityManager;
	private MobileNotification mobileNotification;
	
	private ArrayList<String> tokens;
	
	@Value("${alert.Poll.Delay}")
	private String time;
	
	@Autowired
	public AlertDAOImpl(EntityManager entityManager_, MobileNotification mobileNotification_) {
		entityManager = entityManager_;
		mobileNotification = mobileNotification_;
		tokens = new ArrayList<>();
	}

	@Override
	public List<Alert> findAll() {
		Session session = entityManager.unwrap(Session.class);
		
		Query<Alert> query = session.createQuery("from Alert", Alert.class);
		
		return query == null ? null : query.getResultList();
	}
	
	//Slight delay (0.1s)
	public List<Alert> findMostRecent() {
		Date date = new Date();
		Session session = entityManager.unwrap(Session.class);
		
		Query<Alert> query = session.createQuery("FROM Alert a ORDER BY a.scilaTimestamp DESC", Alert.class);
		
		if(query == null)
			return null;
		
		query.setMaxResults(100);
		List<Alert> results = query.getResultList();
		
		if(time != null)
			if(results.get(0).getScilaTimestamp().compareTo(BigInteger.valueOf(date.getTime()-Integer.parseInt(time))) > 0)
				mobileNotification.sendNotification(tokens);

		return results;
	}

	@Override
	public Alert findByAlertId(String alertId) {
		Session session = entityManager.unwrap(Session.class);
		
		return session.get(Alert.class, alertId);
	}
	
	@Override
	public boolean verifyUser(String username, String password) {
		
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		
		Session session = entityManager.unwrap(Session.class);
		Query<User> query = session.createQuery("FROM User u WHERE u.username = '" + username + "' ", User.class);
		if (!query.getResultList().isEmpty()) {
			for(int i = 0; i < query.getResultList().size(); i++) {
				if (passwordEncryptor.checkPassword(password, query.getResultList().get(i).getHashedPassword()) 
						&& query.getResultList().get(i).getUsername().equals(username)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void addToken(String token) {
		if(!tokens.contains(token))
			tokens.add(token);
	}
}