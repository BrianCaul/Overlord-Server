package com.overlord.controller.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;

import com.overlord.model.Area;
import com.overlord.model.Event;
import com.overlord.model.Position;
import com.overlord.model.Venue;
import com.overlord.service.CompanyService;
import com.overlord.service.EventService;
@Controller
@SessionAttributes("event")
public class EventRestController {

	@Autowired
	private EventService eventService;
	
	@Autowired
	private CompanyService companyService;

	@Autowired
	private View jsonView;


	@RequestMapping(value = "/rest/events", method = RequestMethod.GET)
	public @ResponseBody List<Event> getAllEvents() {
		List<Event> events = null;
		try {
			events = eventService.getAllEvents();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return events;
	}

	@RequestMapping(value = "/rest/events/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteEvent(@PathVariable String id) {

		try {
			eventService.deleteEvent(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Sucessfully Deleted ID: " + id;
	}

	@RequestMapping(value = "/rest/events/{id}", method = RequestMethod.GET)
	public @ResponseBody Event getEvent(@PathVariable String id) {
		Event event = new Event();
		try {
			event = eventService.findByEventId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return event;
	}
	
	@RequestMapping(value = "/rest/events/{id}/visitorcount", method = RequestMethod.GET)
	public @ResponseBody
	int getEventVisitorCount(@PathVariable String id) {
		Event event = new Event();
		try {
			event = eventService.findByEventId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int countVisitors = 0;

		for (Venue venue : event.getVenues()) {

			for (Area area : venue.getAreas()) {
				for (Position position : area.getPositions()) {
					countVisitors = position.getNumVisitors();
				}
			}
		}
		
		return countVisitors;
		
	}

	@RequestMapping(value = "/rest/events", method = RequestMethod.POST)
	public  @ResponseBody Event createEvent(
			@RequestParam("eventName") String eventName,
			@RequestParam("companyId") String companyId,
			@RequestParam("capacity") String capacity,
			@RequestParam("start") String start,
			@RequestParam("end") String end,
			@RequestParam(value="reset", required = false) String reset) {

		Event event = new Event();
		
			try {
				SimpleDateFormat dateformat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
				Date startDate = dateformat.parse(start);
				Date endDate = dateformat.parse(start);
				
				event.setEventName(eventName);
				event.setCapacity(Integer.parseInt(capacity));
				event.setStart(startDate);
				event.setEnd(endDate);
				if(reset!=null){
					Date resetDate = dateformat.parse(reset);
					event.setReset(resetDate);
				}
				event.setEventName(eventName);
				event.setCompany(companyService.findByCompanyId(companyId));
			
				event = eventService.createEvent(event);
			} catch (Exception e) {
				e.printStackTrace();
			}

		return event;
	}
	
		
	@RequestMapping(value = "/rest/events/{id}", method = RequestMethod.POST)
	public @ResponseBody Event updateEvent(@PathVariable String id,
			@RequestParam("eventName") String eventName,
			@RequestParam("companyId") String companyId,
			@RequestParam("capacity") String capacity,
			@RequestParam("start") String start,
			@RequestParam("end") String end,
			@RequestParam(value="reset", required = false) String reset) {

		Event event = new Event();
		try {
			SimpleDateFormat dateformat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
			Date startDate = dateformat.parse(start);
			Date endDate = dateformat.parse(start);
			
			event = eventService.findByEventId(id);
			event.setEventName(eventName);
			event.setCapacity(Integer.parseInt(capacity));
			event.setStart(startDate);
			event.setEnd(endDate);
			if(reset!=null){
				Date resetDate = dateformat.parse(reset);
				event.setReset(resetDate);
			}
			event.setEventName(eventName);
			event.setCompany(companyService.findByCompanyId(companyId));
			event = eventService.updateEvent(event);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return event;
	}

}
