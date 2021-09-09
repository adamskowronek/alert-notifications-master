package com.scila.alertnotification.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.scila.alertnotification.entity.Alert;

@RunWith(SpringRunner.class)
class AlertDAOImplTest {
	
	@InjectMocks
	AlertDAOImpl dao;
	
	@Mock
	EntityManager entityManager;
	
	@Mock
	Session session;
	
	@Mock
	Query<Alert> query;
	
	@Mock
	ArrayList<String> tokens;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@AfterEach
	void reset() {
		Mockito.reset(entityManager, session, query, tokens);
	}

	@Test
	void findAllTest() {
		List<Alert> mockedData = new ArrayList<>();
		
		Alert a = new Alert();
		a.setAlertId("Scila-ABC123");
		mockedData.add(a);
		
		Mockito.when(entityManager.unwrap(Session.class)).thenReturn(session);
		Mockito.when(session.createQuery("from Alert", Alert.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(mockedData);
		
		List<Alert> reference = dao.findAll();
		
		assertThat(reference.get(0).getAlertId()).isEqualTo("Scila-ABC123");
		//assertEquals(reference.get(0).getAlertId(), mockedData.get(0).getAlertId());
	}
	/*
	@SuppressWarnings("deprecation")
	@Test
	void findMostRecentTest() {
		List<Alert> mockedData = new ArrayList<>();
		
		Alert a = new Alert();
		a.setAlertId("SA-123");
		mockedData.add(a);
		
		Mockito.when(entityManager.unwrap(Session.class)).thenReturn(session);
		Mockito.when(session.createQuery("FROM Alert a ORDER BY a.scilaTimestamp DESC", Alert.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(mockedData);
		
		List<Alert> reference = dao.findMostRecent();
		
		assertEquals(reference.get(0).getAlertId(), mockedData.get(0).getAlertId());
	}
	*/
	@Test
	void findByAlertIdTest() {
		String alertId = "SA-456";
		Alert mockedData = new Alert();
		mockedData.setAlertId(alertId);
		
		Mockito.when(entityManager.unwrap(Session.class)).thenReturn(session);
		Mockito.when(session.get(Alert.class, alertId)).thenReturn(mockedData);
		
		Alert reference = dao.findByAlertId(alertId);
		
		assertEquals(reference.getAlertId(), mockedData.getAlertId());
	}
}
