package com.techelevator.npgeek.model.test;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.JDBC.JDBCParkDAO;


public class JDBCParkDAOTest {
	
	private JDBCParkDAO test;
	private DataSource dataSource;
	
	@Before
	public void setup() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		this.test = new JDBCParkDAO(dataSource);
	}
	
	
	
	@Test
	public void getAllParks_returns_values() {
		List<Park> sut = new ArrayList<>();
		sut = test.getAllParks();
		
		Assert.assertEquals(10, sut.size());
	}

}
