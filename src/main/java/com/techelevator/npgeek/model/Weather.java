package com.techelevator.npgeek.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather {

	private String parkCode;
	private int fiveDayForcastValue;
	
	@JsonProperty("temperatureLow")
	private int low;
	
	@JsonProperty("temperatureHigh")
	private int high;
	
	@JsonProperty("icon")
	private String forecast;
	

	public Weather(String parkCode, int fiveDayForcastValue, int low, int high, String forecast) {
		this.parkCode = parkCode;
		this.fiveDayForcastValue = fiveDayForcastValue;
		this.low = low;
		this.high = high;
		this.forecast = forecast;
	}

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public int getFiveDayForcastValue() {
		return fiveDayForcastValue;
	}

	public void setFiveDayForcastValue(int fiveDayForcastValue) {
		this.fiveDayForcastValue = fiveDayForcastValue;
	}

	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
	}

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

	public String getImageName() {
		String result = "";
		if (forecast.contains(" ")) {
			int space = forecast.indexOf(" ");
			result += forecast.substring(0, space);
			String firstChar = Character.toString(forecast.charAt(space + 1)).toUpperCase();
			result += firstChar + forecast.substring(space + 2) + ".png";
		} else {
			result = forecast + ".png";
		}

		return result;
	}
	
	public int getHighCelcius() {
		return (int) ((high - 32) * 0.555556);
	}
	
	public int getLowCelcius() {
		return (int) ((low - 32) * 0.555556);
	}

	public List<String> getReccomendations() {
		List<String> results = new ArrayList<>();
		switch (forecast) {
		case "snow":
			results.add("Pack snowshoes");
			break;
		case "rain":
			results.add("Pack rain gear");
			results.add("Wear waterproof shoes");
			break;
		case "sunny":
			results.add("Pack sun block");
			break;
		case "thunderstorms":
			results.add("Seek shelter");
			results.add("Avoid hiking on exposed ridges");
			break;
		}

		if (high > 75) {
			results.add("Bring an extra gallon of water");
		}

		if ((high - low) > 20) {
			results.add("Wear breathable layers");
		}

		if (low < 20) {
			results.add("Beware of the dangers of exposure to frigid temperatures");
		}

		return results;

	}

}
