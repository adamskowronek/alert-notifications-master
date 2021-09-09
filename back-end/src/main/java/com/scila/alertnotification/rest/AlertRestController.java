package com.scila.alertnotification.rest;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.cliftonlabs.json_simple.JsonKey;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.scila.alertnotification.entity.Alert;
import com.scila.alertnotification.service.AlertService;
import com.scila.alertnotification.notification.*;

/**
 * This class pushes result from method calls to REST.
 */
@EnableScheduling
@RestController
@RequestMapping("/")
public class AlertRestController {

	AlertService alertService;
	
	@Autowired
	public AlertRestController(AlertService alertService_) {
		alertService = alertService_;
	}
	
	/**
	 * Pushes most recent entries within interval @fixedDelayString from db to REST.
	 * @return list of most recent entries.
	 */
	@Scheduled(fixedDelayString = "${alert.Poll.Delay}")
	@GetMapping("/alerts")
	public List<Alert> findMostRecent() {
		return alertService.findMostRecent();
	}
	
	@GetMapping("/findall")
	public List<Alert> findAll() {
		return alertService.findAll();
	}
	
	@GetMapping("/alerts/{alertId}")
	public Alert findByAlertId(@PathVariable String alertId) {
		return alertService.findByAlertId(alertId);
	}
	
	@RequestMapping(value = "/token", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public void recieveToken(@RequestBody JsonObject token) {
		String tokenKey = ((String) ((LinkedHashMap) (token.get("token"))).get("token"));
		
		alertService.addToken(tokenKey);
	}
	
	/**
	 * Fetches login details from front-end and checks database for corresponding login details.
	 * @return login verification.
	 */
	@RequestMapping(value = "/users", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public JsonObject verifyUser(@RequestBody JsonObject payload) {

		if (alertService.verifyUser((String) payload.get("username"), (String) payload.get("password"))) {
			JsonObject result = new JsonObject();
			result.put("success", new Boolean(true));
			result.put("user", (String) payload.get("username"));
			return result;
		}
		JsonObject fail = new JsonObject();
		fail.put("success", new Boolean(false));
		fail.put("message", "Username or password incorrect, please try again.");
		return fail;
	}
}
