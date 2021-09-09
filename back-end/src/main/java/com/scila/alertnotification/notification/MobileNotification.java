package com.scila.alertnotification.notification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import com.scila.alertnotification.rest.AlertRestController;

@Component
public class MobileNotification {
	
	private FirebaseApp app;
	
	@Value("${fcm.json.path}")
	private String fcmKeyPath;
	
	public MobileNotification() {
		
	}
	
	@PostConstruct
	public void init() {
		
		try {
			FileInputStream serviceAccount = new FileInputStream(fcmKeyPath);

			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://scila-alertnotification.firebaseio.com")
					.build();
			
			app = FirebaseApp.initializeApp(options);
		} catch (FileNotFoundException fnfE) {
			System.err.println("FCM configuration file not found, is the key path entered correctly?");
			fnfE.printStackTrace();
		} catch (IOException ioE) {
			ioE.printStackTrace();
		}
	}
	
	public void sendNotification(List<String> tokens) {
		//tokens.add("foOtV0jgIW4:APA91bHU7l3PjI7wwlGhgQ6Jd_dn_B7wOX5wFsTtrY1B0h1T2xVwxg7GLOwjb8RxS180DsAbPEAtvdYd8cYI6hA6s5RAZx9wYLk1gOc1lTLRz7v89aYZyUDCReTAT8gi9_kTLpPYy_3x");
		
		if(tokens.isEmpty())
			return;
		/*
		Message message = Message.builder()
				.setNotification(new Notification("Scila Alert!", "A new alert"))
				.setToken("foOtV0jgIW4:APA91bHU7l3PjI7wwlGhgQ6Jd_dn_B7wOX5wFsTtrY1B0h1T2xVwxg7GLOwjb8RxS180DsAbPEAtvdYd8cYI6hA6s5RAZx9wYLk1gOc1lTLRz7v89aYZyUDCReTAT8gi9_kTLpPYy_3x")
				.build();
		
		*/
		MulticastMessage message = MulticastMessage.builder()
			.setNotification(new Notification("Scila Alert!", "A new alert has been triggered"))
		    .addAllTokens(tokens)
		    .build();
		

		// Send a message to the device corresponding to the provided
		// registration token.
		String response = "";
		try {
//			response = FirebaseMessaging.getInstance().send(message);
			FirebaseMessaging.getInstance().sendMulticast(message);
 			//response = FirebaseMessaging.getInstance().sendAll(message);
 
		} catch (Exception e) {
			// do something with exception
		}
	}
	
	/*
	public void sendNotification() {
		String androidFcmKey="AAAAW_VPO-E:APA91bFrx8uM_Ta_iHDlg3Xyx07OuAefyP8snA80XhkL7WmeMxX9Jg1eB46HqJKt9koJReE1xHDQhbxcKG3PTwjHVs3PHecsxoGfqqngcLOBGWuYkjDf8XqAvZxJ0Gtdu7ZRLWgIJcB-";
		   String androidFcmUrl="https://fcm.googleapis.com/fcm/send";

		   RestTemplate restTemplate = new RestTemplate();
		   HttpHeaders httpHeaders = new HttpHeaders();
		   httpHeaders.set("Authorization", "key=" + androidFcmKey);
		   httpHeaders.set("Content-Type", "application/json");
		   JSONObject msg = new JSONObject();
		   JSONObject json = new JSONObject();

		   msg.put("title", "Title");
		   msg.put("body", "Message");
		   msg.put("notificationType", "Test");

		   json.put("data", msg);
		   // Adam Skowronek device ID
		   json.put("to", "nyaWAheLnIP2qiEK2xJLxDT5Ifn1");

		   HttpEntity<String> httpEntity = new HttpEntity<String>(json.toString(),httpHeaders);
		   String response = restTemplate.postForObject(androidFcmUrl,httpEntity,String.class);
		   System.out.println(response);
	}
	*/
}
