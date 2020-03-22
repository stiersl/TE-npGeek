package com.techelevator.npgeek.model;

import java.util.List;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;



public class SurveyResult {
	
	
	private ParkDAO parkDao;
	
	private int surveyId;
	
	@NotBlank(message="You must select a Park!")
	private String parkCode;
	
	@NotBlank(message="You must enter an email!")
	@Email(message = "That is not a valid email!")
	private String emailAddress;
	
	@NotBlank(message = "You must select a state!")
	private String state;
	
	@AssertTrue(message="You didnt supply a valid state!")
	public boolean isValidState() {
		boolean result = false;
		State[] states = State.values();
		for(State stateToCheck: states) {
			if(state != null && state.equals(stateToCheck.getName())) {
				result = true;
		}
	}
		return result;
	}
	
	@NotBlank(message = "You must select an Activity Level!")
	private String activityLevel;
	
	@AssertTrue(message="You didnt supply a valid activity level!")
	public boolean isValidActivityLevel() {
		boolean result = false;
		ActivityLevel[] activities = ActivityLevel.values();
		for(ActivityLevel toCheck: activities) {
			if(activityLevel != null && activityLevel.equals(toCheck.getName())) {
				result= true;
			}
		}
		return result;
	}
	
	
	
	public SurveyResult() {
	  
	}

	public SurveyResult(int surveyId, String parkCode, String emailAddress, String state, String activityLevel) {
		this.surveyId = surveyId;
		this.parkCode = parkCode;
		this.emailAddress = emailAddress;
		this.state = state;
		this.activityLevel = activityLevel;
	}
	
	public int getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}

}
