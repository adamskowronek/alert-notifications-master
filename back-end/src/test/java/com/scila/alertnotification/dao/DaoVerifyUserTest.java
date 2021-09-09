package com.scila.alertnotification.dao;

import static org.assertj.core.api.Assertions.assertThat;

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

import com.scila.alertnotification.entity.*;

class DaoVerifyUserTest {
	
	@InjectMocks
	AlertDAOImpl dao;
	
	@Mock
	EntityManager entityManager;
	
	@Mock
	Session session;
	
	@Mock
	Query<User> query;
	
	@Mock
	List<User> list;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void verifyUserTest() {
		String username = "John";
		
		Mockito.when(entityManager.unwrap(Session.class)).thenReturn(session);
		Mockito.when(session.createQuery("FROM User u WHERE u.username = '" + username + "' ", User.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(list);
		Mockito.when(list.isEmpty()).thenReturn(true);
		
		boolean result = dao.verifyUser(username, "password");
		
		assertThat(result).isEqualTo(false);
	}

}
