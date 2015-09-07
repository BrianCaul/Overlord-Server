package com.overlord.service;

import java.util.List;

import com.overlord.model.Venue;

public interface VenueService {
	Venue save(Venue venue);
	Venue findByName(String name);
	List<Venue> getAllVenues();
	void deleteVenue(String id);
	Venue createVenue(Venue venue);
	Venue findByVenueId(String id);
	Venue updateVenue(Venue venue);
}
