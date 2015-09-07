package com.overlord.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.overlord.model.Company;

@Repository("companyRepository")
public interface CompanyRepository extends JpaRepository<Company, Integer> {
	
	@Query("select c from Company c where c.name = :name")
	Company findByName(@Param("name") String name);
	
}
