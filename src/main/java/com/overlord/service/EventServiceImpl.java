package com.overlord.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.overlord.model.Company;
import com.overlord.model.Event;
import com.overlord.repository.EventRepository;

@Service("eventService")
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;
	
	@Transactional
	public Event save(Event event) {
		return eventRepository.save(event);
	}

	public Event findByName(String name) {	
		Event event = eventRepository.findByName(name);
		
		if(event != null) {
			return event;
		} 
		
		return event;		
	}


	public List<Event> getAllEvents() {
		return eventRepository.findAll();
	}
	
	@Transactional
	public void deleteEvent(String id) {
		eventRepository.delete(Integer.parseInt(id));
	}
	

	public Event findByEventId(String id) {
		return eventRepository.findOne(Integer.parseInt(id));
	}
	
	@Transactional
	public Event createEvent(Event event) {
		return eventRepository.save(event);
	}
	
	@Transactional
	public Event updateEvent(Event event) {
		return eventRepository.save(event);
	}


}
