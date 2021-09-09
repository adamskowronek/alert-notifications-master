package com.scila.alertnotification.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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

class DaoFindMostRecentTest {

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
	void findMostRecentTest() {		
		Mockito.when(entityManager.unwrap(Session.class)).thenReturn(session);
		Mockito.when(session.createQuery("FROM Alert a ORDER BY a.scilaTimestamp DESC", Alert.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(null);
		
		List<Alert> reference = dao.findMostRecent();
		Mockito.verify(entityManager).unwrap(Session.class);
		Mockito.verify(session).createQuery("FROM Alert a ORDER BY a.scilaTimestamp DESC", Alert.class);
		Mockito.verify(query).getResultList();
		
		assertThat(reference).isEqualTo(null);
	}
}
