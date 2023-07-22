package com.backend.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	public Employee findById(int id);

}
