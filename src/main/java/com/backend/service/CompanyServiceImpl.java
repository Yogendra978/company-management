package com.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.Company;
import com.backend.exception.ResourceNotFoundException;
import com.backend.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public Company createData(Company company) {
		Company save = companyRepository.save(company);
		return save;
	}

	@Override
	public List<Company> getAllInfo() {
		List<Company> findAll = companyRepository.findAll();
		return findAll;
	}

	@Override
	public Company getByID(int id) {
		Company findById = companyRepository.findById(id);//orElseThrow(()-> new ResourceNotFoundException(id+"frgh "));
		return findById;
		
//		Category findById = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id+" this category id not found"));
//		return findById;
		
	}

	@Override
	public void deleteById(int id) {
		companyRepository.deleteById(id);
		
	}

	@Override
	public Company updateByID(int id, Company company) {
		
		Company newCom = new Company();
		newCom.setId(id);
		newCom.setCompanyName(company.getCompanyName());
		newCom.setYear(company.getYear());
		
		Company update = companyRepository.save(newCom);
		return update;
	}

	@Override
	public Company Updated(Company company) {
		Company update = companyRepository.save(company);
		return update;
	}

}
