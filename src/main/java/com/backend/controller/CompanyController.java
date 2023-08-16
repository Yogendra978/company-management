package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backend.entity.Company;
import com.backend.service.CompanyService;


	@Controller
	@RequestMapping("/company")
	public class CompanyController {
		
	@Autowired
	private CompanyService companyService;
	
	//localhost:3030/company/create
	@GetMapping("/create")
	public String createConpanyInFo() {
		
		return "createCompany";
	}
	
	//localhost:3030/company/save
	@PostMapping("/save")
	public String saveInfo(@ModelAttribute("c") Company company,Model model) {
		companyService.createData(company);
		model.addAttribute("msg","Company has been Created !!");
		return "createCompany";
		
	}
	
	//localhost:3030/company/list
	@GetMapping("/list")
	public String listAllUser(Model model) {
		List<Company> listAll = companyService.getAllInfo();
		model.addAttribute("list",listAll);
		
		return "listCompany";
	}
	
	//localhost:3030/company/edit/id
	@GetMapping("/edit/{id}")
	public String updateUser(@PathVariable int id,Model model) {
		Company userById = companyService.getByID(id);
		model.addAttribute("user",userById);
		return "updateCompany";
	}
	
	
	//localhost:3030/company/edit/id
	@PostMapping("/edit/{id}")
	public String updatedCompany(@PathVariable int id,@ModelAttribute("Company") Company Oldcompany , Model model) {
		
		// get user database
		Company company = companyService.updateByID(id, Oldcompany);
		company.setId(id);
		company.setCompanyName(Oldcompany.getCompanyName());
		company.setYear(Oldcompany.getYear());
		
		
		//uptated user
		companyService.Updated(company);
		
		model.addAttribute("msg","Record has been Updated");
		return "redirect:/company/list";
	}
	
	//localhost:3030/company/delete/id
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable int id,Model model) {
		companyService.deleteById(id);
		model.addAttribute("msg","Record has been Deleted");
		return "redirect:/company/list";
	}
	
	

}
