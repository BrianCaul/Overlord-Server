package com.overlord.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.overlord.model.Company;
import com.overlord.model.Venue;

@Repository("venueRepository")
public interface VenueRepository extends JpaRepository<Venue, Integer> {
	
	@Query("select v from Venue v where v.venueName = :venueName")
	Venue findByName(@Param("venueName") String venueName);
}
