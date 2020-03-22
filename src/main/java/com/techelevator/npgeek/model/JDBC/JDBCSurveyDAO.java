package com.techelevator.npgeek.model.JDBC;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.SurveyDAO;
import com.techelevator.npgeek.model.SurveyResult;

@Component
public class JDBCSurveyDAO implements SurveyDAO {
	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCSurveyDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	

	@Override
	public List<SurveyResult> getAllSurveys() {
		List<SurveyResult> surveys = new ArrayList<SurveyResult>();
		String sql = "SELECT surveyid, parkcode, emailaddress, state, activitylevel FROM survey_result;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			SurveyResult surveyResult = new SurveyResult(results.getInt("surveyid"), results.getString("parkcode"),
					results.getString("emailaddress"), results.getString("state"), results.getString("activitylevel"));
			surveys.add(surveyResult);
		}
		
		
		return surveys;
	}

	@Override
	public boolean createSurvey(SurveyResult survey) {
		boolean completed = false;
		String sql = "INSERT INTO survey_result (parkcode, emailaddress, state, activitylevel) " + 
				"VALUES (?, ?, ?, ?) RETURNING surveyid;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, survey.getParkCode(), survey.getEmailAddress(),
				survey.getState(), survey.getActivityLevel());
		if(results.next()) {
			completed = true;
		}
		
		return completed;
	}


	@Override
	public List<Park> getParksOrderedBySurveys() {
		List<Park> results = new ArrayList<>();
		String sql = "SELECT parkname, COUNT(*) AS numberofsurveys, parkcode FROM park JOIN survey_result USING (parkcode) " + 
					"GROUP BY parkcode ORDER BY count(parkCode) DESC, parkname;";
		SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);
		while(srs.next()) {
			Park newPark = new Park();
			newPark.setParkCode(srs.getString("parkcode"));
			newPark.setParkName(srs.getString("parkname"));
			newPark.setNumberOfSurveys(srs.getInt("numberofsurveys"));
			results.add(newPark);
		}
		
		return results;
	}
	
	

}
