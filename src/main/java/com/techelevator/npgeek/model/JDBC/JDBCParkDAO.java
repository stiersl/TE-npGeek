package com.techelevator.npgeek.model.JDBC;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.ParkDAO;

@Component
public class JDBCParkDAO implements ParkDAO {
	
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getAllParks() {
		List<Park> parks = new ArrayList<Park>();
		
		String sql = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail," + 
				"numberofanimalspecies, numberofcampsites, climate, yearfounded, annualvisitorcount, " + 
				"inspirationalquote, inspirationalquotesource, parkdescription, entryfee, longitude, latitude FROM park; ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		
		while(results.next()) {
			parks.add(mapRowToPark(results));
		}
		
		return parks;
	}

	@Override
	public Park getParkByParkCode(String parkCode) {
		Park newPark = new Park();
		
		String sql = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail," + 
				"numberofanimalspecies, numberofcampsites, climate, yearfounded, annualvisitorcount, " + 
				"inspirationalquote, inspirationalquotesource, parkdescription, entryfee, longitude, latitude "
				+ "FROM park WHERE parkcode = ?; ";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkCode);
		
		while(results.next()) {
			newPark = mapRowToPark(results);
		}
		
		return newPark;
	}
	
	private Park mapRowToPark(SqlRowSet row) {
		Park result = new Park();
		
		result.setParkCode(row.getString("parkcode"));
		result.setParkName(row.getString("parkname"));
		result.setState(row.getString("state"));
		result.setAcreage(row.getInt("acreage"));
		result.setElevation(row.getInt("elevationinfeet"));
		result.setMilesOfTrail(row.getDouble("milesoftrail"));
		result.setNumberOfAnimalSpecies(row.getInt("numberofanimalspecies"));
		result.setNumberOfCampsites(row.getInt("numberofcampsites"));
		result.setClimate(row.getString("climate"));
		result.setYearFounded(row.getInt("yearfounded"));
		result.setAnnualVisitorCount(row.getInt("annualvisitorcount"));
		result.setInspirationalQuote(row.getString("inspirationalquote"));
		result.setInspirationalQuoteSource(row.getString("inspirationalquotesource"));
		result.setParkDescription(row.getString("parkdescription"));
		result.setEntryFee(row.getInt("entryfee"));
		result.setLatitude(row.getDouble("latitude"));
		result.setLongitude(row.getDouble("longitude"));
		
		
		return result;
	}

}
