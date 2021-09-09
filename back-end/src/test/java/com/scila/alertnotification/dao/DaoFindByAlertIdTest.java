package com.scila.alertnotification.dao;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.scila.alertnotification.entity.Alert;

class DaoFindByAlertIdTest {

	@InjectMocks
	AlertDAOImpl dao;
	
	@Mock
	EntityManager entityManager;
	
	@Mock
	Session session;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void findByAlertIdTest() {
		String alertId = "SC-HB-456";
		
		Alert mockedData = new Alert();
		mockedData.setAlertId(alertId);
		
		Mockito.when(entityManager.unwrap(Session.class)).thenReturn(session);
		Mockito.when(session.get(Alert.class, alertId)).thenReturn(mockedData);
		
		Alert reference = dao.findByAlertId("SC-HB-456");
		Mockito.verify(entityManager).unwrap(Session.class);
		Mockito.verify(session).get(Alert.class, alertId);
		
		assertThat(reference.getAlertId()).isEqualTo("SC-HB-456");
	}

}
