package com.overlord.controller.rest;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;

import com.overlord.model.Company;
import com.overlord.service.CompanyService;
@Controller
@SessionAttributes("company")
public class CompanyRestController {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private View jsonView;


	@RequestMapping(value = "/rest/companies", method = RequestMethod.GET)
	public @ResponseBody List<Company> getAllCompanies() throws JsonGenerationException, JsonMappingException, IOException {
		List<Company> companies = null;

		try {
			companies = companyService.getAllCompanies();
		} catch (Exception e) {
			e.printStackTrace();  
		}
		return companies;
	}

	@RequestMapping(value = "/rest/companies/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteCompany(@PathVariable String id) {

		try {
			companyService.deleteCompany(id);
		} catch (Exception e) {
			e.printStackTrace();  
		}

		return "Sucessfully Deleted ID: " + id;
	}

	@RequestMapping(value = "/rest/companies/{id}", method = RequestMethod.GET)
	public @ResponseBody Company getCompany(@PathVariable String id) {
		Company company = new Company();
		try {
			company = companyService.findByCompanyId(id);
		} catch (Exception e) {
			e.printStackTrace();  
		}

		return company;
	}

	@RequestMapping(value = "/rest/companies", method = RequestMethod.POST)
	public @ResponseBody Company createCompany(
			@RequestParam("companyName") String companyName,
			@RequestParam("companyRegistrationNumber") String companyReg,
			@RequestParam("companyContactDetail") String companyContactDetail,
			@RequestParam("companyAddress") String companyAddress) {

		Company company = new Company();


			try {
				company.setName(companyName);
				company.setAddress(companyAddress);
				company.setContactDetail(companyContactDetail);
				company.setRegistrationNumber(companyReg);
				company = companyService.createCompany(company);
			} catch (Exception e) {
				e.printStackTrace();  
			}

	

		return company;
	}
	
		
	@RequestMapping(value = "/rest/companies/{id}", method = RequestMethod.POST)
	public @ResponseBody Company updateCompany(@PathVariable String id,
			@RequestParam("companyName") String companyName,
			@RequestParam("companyRegistrationNumber") String companyReg,
			@RequestParam("companyContactDetail") String companyContactDetail,
			@RequestParam("companyAddress") String companyAddress) {

		Company company = new Company();
		try {
			
			company = companyService.findByCompanyId(id);
			company.setName(companyName);
			company.setAddress(companyAddress);
			company.setContactDetail(companyContactDetail);
			company.setRegistrationNumber(companyReg);
			company = companyService.updateCompany(company);
		} catch (Exception e) {
			e.printStackTrace();  
		}

		return company;
	}



}
