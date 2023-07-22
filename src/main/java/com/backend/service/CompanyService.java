package com.backend.service;

import java.util.List;

import com.backend.entity.Company;

public interface CompanyService {
	
	public Company createData(Company company);
	
	public List<Company> getAllInfo();
	
	public Company getByID(int id);
	
	public Company updateByID(int id,Company company);
	
	public void deleteById(int id);

	public Company Updated(Company company);

}
