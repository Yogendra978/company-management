package com.backend.service;

import java.util.List;

import com.backend.entity.Company;
import com.backend.entity.Employee;

public interface EmployeeService {

	public Employee createData(Employee employee,int id);
	
	public List<Employee> getAllInfo();
	
	public Employee getByID(int id);
	
	public Employee updateByID(int eId, Employee employee);
	
	public void deleteById(int id);


	

	
}
