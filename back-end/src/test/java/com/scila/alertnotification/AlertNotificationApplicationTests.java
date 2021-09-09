 package com.scila.alertnotification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.scila.alertnotification.dao.AlertDAOImpl;
import com.scila.alertnotification.entity.Alert;

@RunWith(SpringRunner.class)
@WebMvcTest
@SpringBootTest
public class AlertNotificationApplicationTests {
	
	@InjectMocks
	AlertDAOImpl dao;
	
	@Mock
	EntityManager entityManager;
	
	@Mock
	Session session;
	
	@Mock
	Query<Alert> query;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void findAll() {
		List<Alert> test = new ArrayList<>();
		
		Alert a = new Alert();
		a.setDescription("first alert");
		test.add(a);
		
		Mockito.when(entityManager.unwrap(Session.class)).thenReturn(session);
		Mockito.when(session.createQuery("from Alert", Alert.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(test);
		
		/*
		AlertDAOImpl a = new AlertDAOImpl(new EntityManagerMock(), new MobileNotification());
		ArrayList<String> data = new ArrayList<>();
		
		data.add("one");
		data.add("two");
		data.add("three");
		
		ArrayList<String> result = (ArrayList) a.findAll();
		
		assertEquals(data, result);
		*/
	}
	
	@Test
	public void contextLoads() throws Exception {
		/*
		Session session = null;
		
		Mockito.when(entityManager.unwrap(Session.class)).thenReturn(Collections.empty);
		
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/alerts))")
				.accept(MediaType.APPLICATION_JSON)
		).andReturn();
		*/
	}
}
