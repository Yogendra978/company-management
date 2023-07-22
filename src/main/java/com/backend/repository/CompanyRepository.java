package com.backend.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Company;

public interface CompanyRepository extends  JpaRepository<Company, Integer>{

	public Company findById(int id);
	

}
