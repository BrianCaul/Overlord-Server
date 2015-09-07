package com.overlord.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.overlord.model.Venue;
import com.overlord.repository.VenueRepository;

@Service("venueService")
public class VenueServiceImpl implements VenueService {

	@Autowired
	private VenueRepository venueRepository;
	
	@Transactional
	public Venue save(Venue venue) {
		return venueRepository.save(venue);
	}

	public Venue findByName(String name) {	
		Venue venue = venueRepository.findByName(name);
		
		if(venue != null) {
			return venue;
		} 
		
		return new Venue();		
	}


	public List<Venue> getAllVenues() {
		return venueRepository.findAll();
	}
	
	@Transactional
	public void deleteVenue(String id) {
		venueRepository.delete(Integer.parseInt(id));
	}
	

	public Venue findByVenueId(String id) {
		return venueRepository.findOne(Integer.parseInt(id));
	}
	
	@Transactional
	public Venue createVenue(Venue venue) {
		return venueRepository.save(venue);
	}
	
	@Transactional
	public Venue updateVenue(Venue venue) {
		return venueRepository.save(venue);
	}


}
