package com.overlord.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.overlord.model.Position;

@Repository("positionRepository")
public interface PositionRepository extends JpaRepository<Position, Integer> {
	
	@Query("select p from Position p where p.positionName = :positionName")
	Position findByName(@Param("positionName") String positionName);
	
}
