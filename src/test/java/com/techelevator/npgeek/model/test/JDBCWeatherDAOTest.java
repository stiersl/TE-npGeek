package com.techelevator.npgeek.model.test;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.npgeek.model.Weather;
import com.techelevator.npgeek.model.JDBC.JDBCWeatherDAO;

public class JDBCWeatherDAOTest {
	
	private JDBCWeatherDAO test;
	private DataSource dataSource;
	
	@Before
	public void setup() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		this.test = new JDBCWeatherDAO(dataSource);
	}
	
	@Test
	public void getAllWeathersByParkCode_gets_correct_amount() {
		List<Weather> forecast = new ArrayList<>();
		forecast = test.getWeatherByParkCode("ENP");
		
		Assert.assertEquals(5, forecast.size());
		
		
		
	}
	

}
