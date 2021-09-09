package com.scila.alertnotification.entity;

import java.math.BigInteger;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class represents an alert object.
 */
@Entity
@Table(name="alert")
public class Alert {
	
	@Column(name="typeofalert")
	private String typeOfAlert;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="alertid")
	private String alertId;
	
	@Column(name="alertdate")
	private Date alertDate;
	
	@Column(name="alertinfo")
	private String alertInfo;
	
	@Column(name="alertruleclassname")
	private String alertRuleClassName;
	
	@Column(name="alertruleid")
	private String alertRuleId;
	
	@Column(name="assignedusergroup")
	private String assignedUserGroup;
	
	@Column(name="assignee")
	private String assignee;
	
	@Column(name="description")
	private String description;
	
	// CHECK IF THIS WORKS ???!
	@Column(name="invalidated", columnDefinition="BIT")
	private Boolean invalidated;
	
	@Column(name="marketsummaryendtimestamp")
	private BigInteger marketSummaryEndTimestamp;
	
	@Column(name="marketsummarystarttimestamp")
	private BigInteger marketSummaryStartTimestamp;
	
	@Column(name="name")
	private String name;
	
	@Column(name="obid")
	private String obId;
	
	@Column(name="obreplaystarttimestamp")
	private BigInteger obReplayStartTimestamp;

	@Column(name="obshortname")
	private String obShortName;
	
	@Column(name="priority")
	private String priority;
	
	@Column(name="scilatimestamp")
	private BigInteger scilaTimestamp;
	
	@Column(name="secondaryassignee")
	private String secondaryAssignee;
	
	@Column(name="time")
	private BigInteger time;
	
	@Column(name="timeoffset")
	private Integer timeOffset;
	
	@Column(name="type")
	private String type;
	
	@Column(name="firstprice")
	private BigInteger firstPrice;
	
	@Column(name="firsttradetimestamp")
	private BigInteger firstTradeTimestamp;
	
	@Column(name="isup", columnDefinition="BIT")
	private Boolean isUp;
	
	@Column(name="lastprice")
	private BigInteger lastPrice;
	
	@Column(name="lasttradetimestamp")
	private BigInteger lastTradeTimestamp;
	
	@Column(name="membervolume")
	private BigInteger memberVolume;
	
	@Column(name="numtradesinramping")
	private Integer numTradesInRamping;
	
	@Column(name="totalvolume")
	private BigInteger totalVolume;
	
	public Alert() {
		
	}

	public Alert(String typeOfAlert, Date alertDate, String alertInfo, String alertRuleClassName, String alertRuleId,
			String assignedUserGroup, String assignee, String description, Boolean invalidated,
			BigInteger marketSummaryEndTimestamp, BigInteger marketSummaryStartTimestamp, String name, String obId,
			BigInteger obReplayStartTimestamp, String obShortName, String priority, BigInteger scilaTimestamp,
			String secondaryAssignee, BigInteger time, Integer timeOffset, String type, BigInteger firstPrice,
			BigInteger firstTradeTimestamp, Boolean isUp, BigInteger lastPrice, BigInteger lastTradeTimestamp,
			BigInteger memberVolume, Integer numTradesInRamping, BigInteger totalVolume) {
		this.typeOfAlert = typeOfAlert;
		this.alertDate = alertDate;
		this.alertInfo = alertInfo;
		this.alertRuleClassName = alertRuleClassName;
		this.alertRuleId = alertRuleId;
		this.assignedUserGroup = assignedUserGroup;
		this.assignee = assignee;
		this.description = description;
		this.invalidated = invalidated;
		this.marketSummaryEndTimestamp = marketSummaryEndTimestamp;
		this.marketSummaryStartTimestamp = marketSummaryStartTimestamp;
		this.name = name;
		this.obId = obId;
		this.obReplayStartTimestamp = obReplayStartTimestamp;
		this.obShortName = obShortName;
		this.priority = priority;
		this.scilaTimestamp = scilaTimestamp;
		this.secondaryAssignee = secondaryAssignee;
		this.time = time;
		this.timeOffset = timeOffset;
		this.type = type;
		this.firstPrice = firstPrice;
		this.firstTradeTimestamp = firstTradeTimestamp;
		this.isUp = isUp;
		this.lastPrice = lastPrice;
		this.lastTradeTimestamp = lastTradeTimestamp;
		this.memberVolume = memberVolume;
		this.numTradesInRamping = numTradesInRamping;
		this.totalVolume = totalVolume;
	}

	public String getTypeOfAlert() {
		return typeOfAlert;
	}

	public void setTypeOfAlert(String typeOfAlert) {
		this.typeOfAlert = typeOfAlert;
	}

	public String getAlertId() {
		return alertId;
	}

	public void setAlertId(String alertId) {
		this.alertId = alertId;
	}

	public Date getAlertDate() {
		return alertDate;
	}

	public void setAlertDate(Date alertDate) {
		this.alertDate = alertDate;
	}

	public String getAlertInfo() {
		return alertInfo;
	}

	public void setAlertInfo(String alertInfo) {
		this.alertInfo = alertInfo;
	}

	public String getAlertRuleClassName() {
		return alertRuleClassName;
	}

	public void setAlertRuleClassName(String alertRuleClassName) {
		this.alertRuleClassName = alertRuleClassName;
	}

	public String getAlertRuleId() {
		return alertRuleId;
	}

	public void setAlertRuleId(String alertRuleId) {
		this.alertRuleId = alertRuleId;
	}

	public String getAssignedUserGroup() {
		return assignedUserGroup;
	}

	public void setAssignedUserGroup(String assignedUserGroup) {
		this.assignedUserGroup = assignedUserGroup;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getInvalidated() {
		return invalidated;
	}

	public void setInvalidated(Boolean invalidated) {
		this.invalidated = invalidated;
	}

	public BigInteger getMarketSummaryEndTimestamp() {
		return marketSummaryEndTimestamp;
	}

	public void setMarketSummaryEndTimestamp(BigInteger marketSummaryEndTimestamp) {
		this.marketSummaryEndTimestamp = marketSummaryEndTimestamp;
	}

	public BigInteger getMarketSummaryStartTimestamp() {
		return marketSummaryStartTimestamp;
	}

	public void setMarketSummaryStartTimestamp(BigInteger marketSummaryStartTimestamp) {
		this.marketSummaryStartTimestamp = marketSummaryStartTimestamp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObId() {
		return obId;
	}

	public void setObId(String obId) {
		this.obId = obId;
	}

	public BigInteger getObReplayStartTimestamp() {
		return obReplayStartTimestamp;
	}

	public void setObReplayStartTimestamp(BigInteger obReplayStartTimestamp) {
		this.obReplayStartTimestamp = obReplayStartTimestamp;
	}

	public String getObShortName() {
		return obShortName;
	}

	public void setObShortName(String obShortName) {
		this.obShortName = obShortName;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public BigInteger getScilaTimestamp() {
		return scilaTimestamp;
	}

	public void setScilaTimestamp(BigInteger scilaTimestamp) {
		this.scilaTimestamp = scilaTimestamp;
	}

	public String getSecondaryAssignee() {
		return secondaryAssignee;
	}

	public void setSecondaryAssignee(String secondaryAssignee) {
		this.secondaryAssignee = secondaryAssignee;
	}

	public BigInteger getTime() {
		return time;
	}

	public void setTime(BigInteger time) {
		this.time = time;
	}

	public Integer getTimeOffset() {
		return timeOffset;
	}

	public void setTimeOffset(Integer timeOffset) {
		this.timeOffset = timeOffset;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigInteger getFirstPrice() {
		return firstPrice;
	}

	public void setFirstPrice(BigInteger firstPrice) {
		this.firstPrice = firstPrice;
	}

	public BigInteger getFirstTradeTimestamp() {
		return firstTradeTimestamp;
	}

	public void setFirstTradeTimestamp(BigInteger firstTradeTimestamp) {
		this.firstTradeTimestamp = firstTradeTimestamp;
	}

	public Boolean getIsUp() {
		return isUp;
	}

	public void setIsUp(Boolean isUp) {
		this.isUp = isUp;
	}

	public BigInteger getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(BigInteger lastPrice) {
		this.lastPrice = lastPrice;
	}

	public BigInteger getLastTradeTimestamp() {
		return lastTradeTimestamp;
	}

	public void setLastTradeTimestamp(BigInteger lastTradeTimestamp) {
		this.lastTradeTimestamp = lastTradeTimestamp;
	}

	public BigInteger getMemberVolume() {
		return memberVolume;
	}

	public void setMemberVolume(BigInteger memberVolume) {
		this.memberVolume = memberVolume;
	}

	public Integer getNumTradesInRamping() {
		return numTradesInRamping;
	}

	public void setNumTradesInRamping(Integer numTradesInRamping) {
		this.numTradesInRamping = numTradesInRamping;
	}

	public BigInteger getTotalVolume() {
		return totalVolume;
	}

	public void setTotalVolume(BigInteger totalVolume) {
		this.totalVolume = totalVolume;
	}

	@Override
	public String toString() {
		return "Alert [typeOfAlert=" + typeOfAlert + ", alertId=" + alertId + ", alertDate=" + alertDate
				+ ", alertInfo=" + alertInfo + ", alertRuleClassName=" + alertRuleClassName + ", alertRuleId="
				+ alertRuleId + ", assignedUserGroup=" + assignedUserGroup + ", assignee=" + assignee + ", description="
				+ description + ", invalidated=" + invalidated + ", marketSummaryEndTimestamp="
				+ marketSummaryEndTimestamp + ", marketSummaryStartTimestamp=" + marketSummaryStartTimestamp + ", name="
				+ name + ", obId=" + obId + ", obReplayStartTimestamp=" + obReplayStartTimestamp + ", obShortName="
				+ obShortName + ", priority=" + priority + ", scilaTimestamp=" + scilaTimestamp + ", secondaryAssignee="
				+ secondaryAssignee + ", time=" + time + ", timeOffset=" + timeOffset + ", type=" + type
				+ ", firstPrice=" + firstPrice + ", firstTradeTimestamp=" + firstTradeTimestamp + ", isUp=" + isUp
				+ ", lastPrice=" + lastPrice + ", lastTradeTimestamp=" + lastTradeTimestamp + ", memberVolume="
				+ memberVolume + ", numTradesInRamping=" + numTradesInRamping + ", totalVolume=" + totalVolume + "]";
	}
}
