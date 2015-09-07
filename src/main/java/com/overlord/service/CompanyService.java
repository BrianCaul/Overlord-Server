package com.overlord.service;

import java.util.List;

import com.overlord.model.Company;

public interface CompanyService {
	Company save(Company company);
	Company findByName(String name);
	List<Company> getAllCompanies();
	void deleteCompany(String id);
	Company createCompany(Company company);
	Company findByCompanyId(String id);
	Company updateCompany(Company company);
}
