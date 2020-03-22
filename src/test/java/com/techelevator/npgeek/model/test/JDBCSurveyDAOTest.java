package com.techelevator.npgeek.model.test;



import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.npgeek.model.SurveyResult;
import com.techelevator.npgeek.model.JDBC.JDBCSurveyDAO;



public class JDBCSurveyDAOTest {
	
	private JDBCSurveyDAO test;
	private static SingleConnectionDataSource dataSource;
	
	

	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
		
	}
	
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	@Before
	public void setup() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		test = new JDBCSurveyDAO(dataSource);
		jdbcTemplate.update("TRUNCATE survey_result");
	}
	
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void createSurvey_works() {
		SurveyResult surveyResult = new SurveyResult(1, "ENP", "test@test.net", "Ohio", "active");
		boolean result = test.createSurvey(surveyResult);
		Assert.assertTrue(result);
	}
	
	@Test
	public void getAllSurveys_works() {
		SurveyResult surveyResult = new SurveyResult(1, "ENP", "test@test.net", "Ohio", "active");
		test.createSurvey(surveyResult);
		List<SurveyResult> results = test.getAllSurveys();
		
		Assert.assertEquals(1, results.size());

	}
	
	

}
