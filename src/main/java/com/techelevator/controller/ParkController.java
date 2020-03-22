package com.techelevator.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.npgeek.model.ActivityLevel;
import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.ParkDAO;
import com.techelevator.npgeek.model.State;
import com.techelevator.npgeek.model.SurveyDAO;
import com.techelevator.npgeek.model.SurveyResult;
import com.techelevator.npgeek.model.WeatherDAO;
import com.techelevator.npgeek.model.JDBC.JDBCParkDAO;
import com.techelevator.npgeek.model.JDBC.JDBCSurveyDAO;

@Controller
public class ParkController {

	@Autowired
	private ParkDAO parkDao;

	@Autowired
	private WeatherDAO weatherDao;

	@Autowired
	private SurveyDAO surveyDao;

	@GetMapping("/")
	public String loadHomePage(ModelMap mm) {

		mm.put("parks", parkDao.getAllParks());

		return "home";
	}

	@GetMapping("/parkDetails")
	public String loadDetailPage(ModelMap mm, @RequestParam String id) {
		
		Park detailPark =  parkDao.getParkByParkCode(id);

		mm.put("park", detailPark);
		mm.put("weather", weatherDao.getWeatherByCoordinates(detailPark.getLongitude(), detailPark.getLatitude()));

		return "detail";
	}

	@PostMapping("/parkDetails")
	public String changeDetailPage(HttpSession session, @RequestParam String temp, @RequestParam String id) {

		session.setAttribute("temperature", temp);

		return "redirect:/parkDetails?id=" + id;
	}

	@RequestMapping(path = "/survey", method = RequestMethod.GET)
	public String showSurveyForm(ModelMap mm) {

		mm.put("parks", parkDao.getAllParks());
		mm.put("states", State.values());
		mm.put("activities", ActivityLevel.values());

		if (mm.containsAttribute("surveyData") == false) {
			SurveyResult empty = new SurveyResult();
			mm.put("surveyData", empty);
		}
		return "survey";
	}

	@RequestMapping(path = "/survey", method = RequestMethod.POST)
	public String processSurveyForm(@Valid @ModelAttribute SurveyResult surveyResult, BindingResult result,
			RedirectAttributes ra) {

		boolean parkNameValid = false;
		List<Park> parks = parkDao.getAllParks();
		for (Park park : parks) {
			if (surveyResult.getParkCode() != null && surveyResult.getParkCode().equals(park.getParkCode())) {
				parkNameValid = true;
			}
		}

		if (result.hasErrors()) {
			ra.addFlashAttribute("surveyData", surveyResult);
			ra.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "surveyData", result);
			return "redirect:/survey";
		} else if (parkNameValid == false) {
			ra.addFlashAttribute("surveyData", surveyResult);
			ra.addFlashAttribute("parkError", "The park you selected is invalid!");
			return "redirect:/survey";
			
		}else {
			surveyDao.createSurvey(surveyResult);
			return "redirect:/favorites";
		}
	}

	// Return the favorites view
	@RequestMapping(path = "/favorites", method = RequestMethod.GET)
	public String showFavoritesPage(ModelMap mm) {

		mm.put("parks", surveyDao.getParksOrderedBySurveys());

		return "favorites";
	}

}
