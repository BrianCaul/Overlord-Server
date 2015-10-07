package com.overlord.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.overlord.model.Stat;
import com.overlord.model.Visitor;

@Repository("visitorRepository")
public interface VisitorRepository extends JpaRepository<Visitor, Integer> {
	
	@Query("select a from Visitor a where a.position.area.event.id = :eventId order by a.entryTime desc")
	List<Stat> findAllVisitors(@Param("eventId") int eventId);
	
}
