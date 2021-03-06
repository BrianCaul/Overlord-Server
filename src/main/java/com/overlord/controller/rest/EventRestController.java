package com.overlord.controller.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
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
	public @ResponseBody List<Event> getAllEvents(@RequestParam(value="companyId", required = true) String companyId) {
		List<Event> events = null;
		try {
			events = eventService.getAllEvents(companyId);
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

		for (Area area : event.getAreas()) {
			for (Position position : area.getPositions()) {
				countVisitors = position.getNumVisitors();
			}
		}
		
		return countVisitors;
		
	}

	@RequestMapping(value = "/rest/events", method = RequestMethod.POST)
	public  @ResponseBody Event createEvent(
			@RequestParam("eventName") String eventName,
			@RequestParam(value="description", required = false) String description,
			@RequestParam("companyId") String companyId,
			@RequestParam("capacity") String capacity,
			@RequestParam("start") String start,
			@RequestParam("end") String end,
			@RequestParam(value="reset", required = false) String reset) {

		Event event = new Event();

			try {
				start = start +":01.000Z";
				end = end + ":01.000Z";
				
				DateTimeFormatter parser = ISODateTimeFormat.dateTime();
		        DateTime startDateTime = parser.parseDateTime(start);
		        DateTime endDateTime = parser.parseDateTime(end);

				
				
				event.setEventName(eventName);
				event.setCapacity(Integer.parseInt(capacity));
				event.setStart(startDateTime.toDate());
				event.setEnd(endDateTime.toDate());
				if(reset != null){
					 DateTime resetDateTime = parser.parseDateTime(start);
					event.setReset(resetDateTime.toDate());
				}
				if(description != null){
					event.setDescription(description);
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
			@RequestParam(value="description", required = false) String description,
			@RequestParam("companyId") String companyId,
			@RequestParam("capacity") String capacity,
			@RequestParam("start") String start,
			@RequestParam("end") String end,
			@RequestParam(value="reset", required = false) String reset) {

		Event event = new Event();
		try {
			start = start +":01.000Z";
			end = end + ":01.000Z";
			
			DateTimeFormatter parser = ISODateTimeFormat.dateTime();
	        DateTime startDateTime = parser.parseDateTime(start);
	        DateTime endDateTime = parser.parseDateTime(end);

			event.setEventName(eventName);
			event.setCapacity(Integer.parseInt(capacity));
			event.setStart(startDateTime.toDate());
			event.setEnd(endDateTime.toDate());
			if(reset != null){
				 DateTime resetDateTime = parser.parseDateTime(start);
				event.setReset(resetDateTime.toDate());
			}
			if(description != null){
				event.setDescription(description);
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
