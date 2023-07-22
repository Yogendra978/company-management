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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.entity.Company;
import com.backend.entity.Employee;
import com.backend.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {
	
	@Autowired
	private EmployeeService employeeService;
	
	//http://localhost:3030/api/employee/create
	@PostMapping("/create/{id}")
	@ResponseBody
	public Employee createEmployee(@RequestBody Employee employee,@PathVariable int id) {
		Employee createData = employeeService.createData(employee,id);
		return createData;
	}
	
	//http://localhost:3030/api/employee/list
	@GetMapping("/list")
	public List<Employee> listInfo() {
		List<Employee> allInfo = employeeService.getAllInfo();
		return allInfo;
	}
	
	//http://localhost:3030/api/employee/list/id
	@GetMapping("/list/{id}")
	public Employee getById(@PathVariable int id) {
		Employee byID = employeeService.getByID(id);
		return byID;
	}
	
	//http://localhost:3030/api/employee/update/eId
	@PutMapping("/update/{eId}")
	public Employee updateCompany(@PathVariable int eId,@RequestBody Employee employee,@RequestParam Company com) {
		Employee updateByID = employeeService.updateByID(eId,employee);
		return updateByID;
	}
	
	//http://localhost:3030/api/employee/delete/id
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable int id) {
		employeeService.deleteById(id);
	}

}
