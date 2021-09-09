package com.scila.alertnotification.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.scila.alertnotification.entity.Alert;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class DaoFindAllTest {
	
	@InjectMocks
	AlertDAOImpl dao;
	
	@Mock
	EntityManager entityManager;
	
	@Mock
	Session session;
	
	@Mock
	Query<Alert> query;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void findAllTest() {
		setup();
		List<Alert> list = new ArrayList<>();
		
		Alert mockedData = new Alert();
		mockedData.setAlertId("Scila-ABC123");
		list.add(mockedData);
		
		Mockito.when(entityManager.unwrap(Session.class)).thenReturn(session);
		Mockito.when(session.createQuery("from Alert", Alert.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(list);
		
		List<Alert> reference = dao.findAll();
		
		assertThat(reference.get(0).getAlertId()).isEqualTo("Scila-ABC123");
	}
}
