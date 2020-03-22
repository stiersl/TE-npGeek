package com.techelevator.npgeek.model.JDBC;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.Weather;
import com.techelevator.npgeek.model.WeatherDAO;


public class JDBCWeatherDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCWeatherDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	public List<Weather> getWeatherByParkCode(String parkCode) {
		List<Weather> weathers = new ArrayList<Weather>();
		String sql = "SELECT parkcode, fivedayforecastvalue, low, high, forecast " + 
				"FROM weather WHERE parkcode = ? ORDER BY fivedayforecastvalue;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkCode);
		while(results.next()) {
			Weather day = new Weather(results.getString("parkcode"),
					results.getInt("fivedayforecastvalue"), results.getInt("low"), 
					results.getInt("high"), results.getString("forecast"));
			weathers.add(day);
		}
		
		return weathers;
	}

}
