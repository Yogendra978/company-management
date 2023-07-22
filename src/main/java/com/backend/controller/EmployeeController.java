package com.backend.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.backend.entity.Company;
import com.backend.entity.Employee;
import com.backend.repository.CompanyRepository;
import com.backend.repository.EmployeeRepository;
import com.backend.service.CompanyService;
import com.backend.service.EmployeeService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;



@Controller
public class EmployeeController {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	
	//localhost:3030/createEmp
	@GetMapping("/createEmp")
	public String createUser() {
		return "createEmployee";
	}
	
	
	//localhost:3030/saveall
	@PostMapping("/saveall")
	public String saveUser(@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,
			@RequestParam("email") String email,@RequestParam("company") int company,Model model) {
			Company Id = companyRepository.findById(company);
		if(Id !=null) {
		Employee employee2 = new Employee();
		employee2.setFirstName(firstName);
		employee2.setLastName(lastName);
		employee2.setEmail(email);
		employee2.setCompany(Id);
		employeeRepository.save(employee2);
		model.addAttribute("msg", "Record is saved!");
		return "createEmployee";
		}else {
			
			model.addAttribute("msg","Category Id Not found !!");
			return "createEmployee";
		}
		
	}
	
	//localhost:3030/listAll
	@GetMapping("/listAll")
	public String listAll(Model model) {
		List<Employee> listAll = employeeRepository.findAll();
		
		model.addAttribute("list",listAll);
		return "listEmployee";
	}
	
	
	//localhost:3030/updateEmp/id
	@GetMapping("/updateEmp/{emp_id}")
	public String updateEmployee(@PathVariable int emp_id ,Model model) {
		Employee findById = employeeRepository.findById(emp_id);   
		model.addAttribute("emp",findById);
		return "updateEmployee";
	}
	
	
	//localhost:3030/editEmp/id
	@PostMapping("/editEmp/{id}")
	public String updatedEmployee(@PathVariable int id,@ModelAttribute("emp") Employee employee) {
		
		// get user database
		Employee newEmp = new Employee();
		newEmp.setId(id);
		newEmp.setCompany(employee.getCompany());
		newEmp.setFirstName(employee.getFirstName());
		newEmp.setLastName(employee.getLastName());
		newEmp.setEmail(employee.getEmail());
		
		//uptated user
		Employee updateEmp = employeeRepository.save(newEmp);
		
		return "redirect:/listAll";
	}
	
	//localhost:3030/deleteEmp/id
	@GetMapping("/deleteEmp/{id}")
	public String deleteUser(@PathVariable int id) {
		employeeRepository.deleteById(id);
		return "redirect:/listAll";
	}
}
