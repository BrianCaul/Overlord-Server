package com.overlord.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.overlord.model.Stat;

@Repository("statRepository")
public interface StatRepository extends JpaRepository<Stat, Integer> {
	
	@Query("select a from Stat a where a.date > :statStartDate AND a.date < :statEndDate AND a.companyId = :companyId AND a.eventId = :eventId order by a.date desc")
	List<Stat> findStats(@Param("statStartDate") Date statStartDate, @Param("statEndDate") Date statEndDate, @Param("companyId") int companyId, @Param("eventId") int eventId);
	
	@Query("select a from Stat a where a.companyId = :companyId AND a.eventId = :eventId order by a.date desc")
	List<Stat> findAllStats(@Param("companyId") int companyId, @Param("eventId") int eventId);
	
}
