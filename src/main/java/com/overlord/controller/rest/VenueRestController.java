package com.overlord.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.overlord.model.Company;
import com.overlord.model.Venue;
import com.overlord.service.CompanyService;
import com.overlord.service.EventService;
import com.overlord.service.VenueService;
@Controller
@SessionAttributes("venue")
public class VenueRestController {

	@Autowired
	private VenueService venueService;
	
	@Autowired
	private EventService eventService;

	@Autowired
	private View jsonView;

	private static final String DATA_FIELD = "data";
	private static final String ERROR_FIELD = "error";

	@RequestMapping(value = "/rest/venues", method = RequestMethod.GET)
	public @ResponseBody List<Venue> getAllVenues() {
		List<Venue> venues = null;

		try {
			venues = venueService.getAllVenues();
		} catch (Exception e) {
			String sMessage = "Error invoking venues";
			return venues;
		}

		return venues;
	}

	@RequestMapping(value = "/rest/venues/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteVenue(@PathVariable String id) {

		try {
			venueService.deleteVenue(id);
		} catch (Exception e) {
			String sMessage = "Error invoking delete venues";
			return sMessage;
		}

		return "Sucessfully Deleted ID: " + id;
	}

	@RequestMapping(value = "/rest/venues/{id}", method = RequestMethod.GET)
	public @ResponseBody Venue getVenue(@PathVariable String id) {
		Venue venue = new Venue();
		try {
			venue = venueService.findByVenueId(id);
		} catch (Exception e) {
			String sMessage = "Error invoking find venue by id";
			return venue;
		}

		return venue;
	}

	@RequestMapping(value = "/rest/venues", method = RequestMethod.POST)
	public @ResponseBody Venue createVenue(
			@RequestParam("venueName") String venueName,
			@RequestParam("capacity") String capacity,
			@RequestParam("eventId") String eventId) {

		Venue venue = new Venue();
			try {
				venue.setVenueName(venueName);
				venue.setCapacity(Integer.parseInt(capacity));
				venue.setEvent(eventService.findByEventId(eventId));
			
				venue = venueService.createVenue(venue);
			} catch (Exception e) {
				String sMessage = "Error creating Venue";
				return venue;
			}

		return venue;
	}
	
		
	@RequestMapping(value = "/rest/venues/{id}", method = RequestMethod.POST)
	public @ResponseBody Venue updateVenue(@PathVariable String id,
			@RequestParam("venueName") String venueName,
			@RequestParam("capacity") String capacity) {

		Venue venue = new Venue();
		try {
			
			venue = venueService.findByVenueId(id);
			venue.setVenueName(venueName);
			venue.setCapacity(Integer.parseInt(capacity));
			venue = venueService.updateVenue(venue);
		} catch (Exception e) {
			String sMessage = "Error updating venue";
			return venue;
		}

		return venue;
	}

	/**
	 * Create an error REST response.
	 * 
	 * @param sMessage
	 * @return
	 */
	private ModelAndView getErrorJSON(String sMessage) {
		return new ModelAndView(jsonView, ERROR_FIELD, sMessage);
	}

}
