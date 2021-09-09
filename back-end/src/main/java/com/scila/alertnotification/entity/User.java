package com.scila.alertnotification.entity;

import java.math.BigInteger;
import java.sql.Date;
import javax.persistence.*;

/**
 * This class represents a user object.
 */
@Entity
@Table(name="scilauser")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private BigInteger id;
	
	@Column(name="accountexpirationdate")
	private Date accountExpirationDate;
	
	@Column(name="displayname")
	private String displayName;
	
	@Column(name="enabled", columnDefinition="BIT")
	private Boolean enabled;
	
	@Column(name="hashedpassword")
	private String hashedPassword;
	
	@Column(name="locked", columnDefinition="BIT")
	private Boolean locked;
	
	@Column(name="numberoffailedloginattempts")
	private Integer numberOfFailedLoginAttempts;
	
	@Column(name="passwordexpirationdate")
	private Date passwordExpirationDate;
	
	@Column(name="type")
	private String type;
	
	@Column(name="username")
	private String username;
	
	@Column(name="email")
	private String email;
	
	public User() {
		
	}

	public User(BigInteger id, Date accountExpirationDate, String displayName, Boolean enabled, String hashedPassword,
			Boolean locked, Integer numberOfFailedLoginAttempts, Date passwordExpirationDate, String type,
			String username, String email) {
		this.id = id;
		this.accountExpirationDate = accountExpirationDate;
		this.displayName = displayName;
		this.enabled = enabled;
		this.hashedPassword = hashedPassword;
		this.locked = locked;
		this.numberOfFailedLoginAttempts = numberOfFailedLoginAttempts;
		this.passwordExpirationDate = passwordExpirationDate;
		this.type = type;
		this.username = username;
		this.email = email;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Date getAccountExpirationDate() {
		return accountExpirationDate;
	}

	public void setAccountExpirationDate(Date accountExpirationDate) {
		this.accountExpirationDate = accountExpirationDate;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Integer getNumberOfFailedLoginAttempts() {
		return numberOfFailedLoginAttempts;
	}

	public void setNumberOfFailedLoginAttempts(Integer numberOfFailedLoginAttempts) {
		this.numberOfFailedLoginAttempts = numberOfFailedLoginAttempts;
	}

	public Date getPasswordExpirationDate() {
		return passwordExpirationDate;
	}

	public void setPasswordExpirationDate(Date passwordExpirationDate) {
		this.passwordExpirationDate = passwordExpirationDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", accountExpirationDate=" + accountExpirationDate + ", displayName=" + displayName
				+ ", enabled=" + enabled + ", hashedPassword=" + hashedPassword + ", locked=" + locked
				+ ", numberOfFailedLoginAttempts=" + numberOfFailedLoginAttempts + ", passwordExpirationDate="
				+ passwordExpirationDate + ", type=" + type + ", username=" + username + ", email=" + email + "]";
	}
}
