package com.techelevator.npgeek.model.Rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.techelevator.npgeek.model.Weather;
import com.techelevator.npgeek.model.WeatherDAO;

@Component
public class RestWeatherDao implements WeatherDAO{
	
	private RestTemplate restTemplate = new RestTemplate();
	
	private static final String API_ROOT = "https://api.darksky.net/forecast/795801a36e5f513584519819d5eb5977/";
	private static final String API_TAIL = "?exclude=currently,minutely,hourly,alerts,flags";
	
	private static class DarkSkyDataPoint {
		
		public double temperatureHigh;
		public double temperatureLow;
		public String icon;
		
		
		
	}
	
	private static class DarkSkyDataBlock {
		public List<DarkSkyDataPoint> data;
	}
	
	private static class DarkSkyDataForecast {
		public DarkSkyDataBlock daily;
	}

	@Override
	public List<Weather> getWeatherByCoordinates(double longitude, double latitude) {
		List<Weather> allWeather = new ArrayList<Weather>();
		int counter = 1;
		
		String url = API_ROOT + latitude + "," + longitude + API_TAIL;
		
		try {
			DarkSkyDataForecast result = restTemplate.getForObject(url, DarkSkyDataForecast.class);
			for(int i = 0; i < 5; i++) {
				DarkSkyDataPoint dsp = result.daily.data.get(i);
				Weather weather = new Weather("", counter++,(int) dsp.temperatureLow,(int) dsp.temperatureHigh, dsp.icon);
				allWeather.add(weather);
			}
		} catch (HttpClientErrorException e) {
			// Do nothing and null list will be returned
		}
		
		return allWeather;
	}

	
	
}
