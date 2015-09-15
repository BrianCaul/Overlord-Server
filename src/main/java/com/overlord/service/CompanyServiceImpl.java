package com.overlord.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.overlord.model.Company;
import com.overlord.repository.CompanyRepository;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private EventService eventService;
	
	@Transactional
	public Company save(Company company) {
		return companyRepository.save(company);
	}

	public Company findByName(String name) {	
		Company company = companyRepository.findByName(name);
		
		if(company != null) {
			return company;
		} 
		
		return company;		
	}


	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}
	
	@Transactional
	public void deleteCompany(String id) {
		companyRepository.delete(Integer.parseInt(id));
	}
	

	public Company findByCompanyId(String id) {
		return companyRepository.findOne(Integer.parseInt(id));
	}
	
	@Transactional
	public Company createCompany(Company company) {
		return companyRepository.save(company);
	}
	
	@Transactional
	public Company updateCompany(Company company) {
		return companyRepository.save(company);
	}


}
