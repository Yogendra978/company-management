package com.backend.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.backend.entity.Company;
import com.backend.entity.Employee;
import com.backend.exception.ResourceNotFoundException;
import com.backend.repository.CompanyRepository;
import com.backend.repository.EmployeeRepository;
@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public Employee createData(Employee employee,int id) {
		Company findById = companyRepository.findById(id);
		employee.setCompany(findById);
		Employee save = employeeRepository.save(employee);
		return save;
	}

	@Override
	public List<Employee> getAllInfo() {
		List<Employee> findAll = employeeRepository.findAll();
		return findAll;
	}

	@Override
	public Employee getByID(int id) {
		Employee findById = employeeRepository.findById(id);
			return findById;	
				//.orElseThrow(()-> new ResourceNotFoundException(id));
		
	}

	@Override
	public Employee updateByID(int eId, Employee employee) {
		Employee emp = new Employee();
		//Company findById = companyRepository.findById(cId);
		emp.setId(eId);
		emp.setCompany(employee.getCompany());
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setEmail(employee.getEmail());
		
		Employee update = employeeRepository.save(emp);
		return update;
	}

	@Override
	public void deleteById(int id) {
		employeeRepository.deleteById(id);
		
	}


	

}
