package com.overlord.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.overlord.model.Area;

@Repository("areaRepository")
public interface AreaRepository extends JpaRepository<Area, Integer> {
	
	@Query("select a from Area a where a.areaName = :areaName")
	Area findByName(@Param("areaName") String areaName);
	
}
