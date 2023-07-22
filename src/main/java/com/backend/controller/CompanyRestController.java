package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.entity.Company;
import com.backend.service.CompanyService;

@RestController
@RequestMapping("/api/company")
public class CompanyRestController {
	
	@Autowired
	private CompanyService companyService;
	
	//http://localhost:3030/api/company/create
	@PostMapping("/create")
	@ResponseBody
	public Company createInfo(@RequestBody Company company) {
		Company createData = companyService.createData(company);
		return createData;
	}
	
	//http://localhost:3030/api/company/list
	@GetMapping("/list")
	public List<Company> listInfo() {
		List<Company> allInfo = companyService.getAllInfo();
		return allInfo;
	}
	
	//http://localhost:3030/api/company/list/id
	@GetMapping("/list/{id}")
	public Company getById(@PathVariable int id) {
		Company byID = companyService.getByID(id);
		return byID;
	}
	
	//http://localhost:3030/api/company/update/id
	@PutMapping("/update/{id}")
	public Company updateCompany(@PathVariable int id,@RequestBody Company company) {
		Company updateByID = companyService.updateByID(id, company);
		return updateByID;
	}
	
	//http://localhost:3030/api/company/delete/id
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable int id) {
		companyService.deleteById(id);
	}

}
