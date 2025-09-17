package com.embarkx.companyms.company.service.impl;


import com.embarkx.companyms.company.clients.ReviewClient;
import com.embarkx.companyms.company.dto.ReviewMessage;
import com.embarkx.companyms.company.entity.Company;
import com.embarkx.companyms.company.exception.CompanyNotFoundException;
import com.embarkx.companyms.company.repository.CompanyRepository;
import com.embarkx.companyms.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {


    @Autowired
    private CompanyRepository companyRepository;


    @Autowired
    private ReviewClient reviewClient;


    @Override
    public List<Company> getAllCompanies() {
        return this.companyRepository.findAll();
    }

    @Override
    public Company createCompany(Company company) {
        return this.companyRepository.save(company);
    }

    @Override
    public Company getSingleCompany(Long companyId) {
        return this.companyRepository.findById(companyId).orElseThrow(()->new CompanyNotFoundException("Company Not Found : "+companyId));
    }

    @Override
    public void deleteCompany(Long companyId) {
        Company company = this.companyRepository.findById(companyId).orElseThrow(()->new CompanyNotFoundException("Company not exists with an ID : "+companyId));
        this.companyRepository.delete(company);
    }

    @Override
    public Company updateCompany(Long companyId, Company company) {

        Optional<Company> optionalCompany = this.companyRepository.findById(companyId);
        Company companyToUpdate = null;
        if(optionalCompany.isPresent()){
            companyToUpdate = optionalCompany.get();
            companyToUpdate.setDescription(company.getDescription());
            companyToUpdate.setName(company.getName());
            return companyRepository.save(companyToUpdate);
        }
        return companyToUpdate;
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        Company company = companyRepository.findById(reviewMessage.getCompanyId()).orElseThrow(()->new CompanyNotFoundException("Company Not Found : "+reviewMessage.getCompanyId()));
        double averageRating = reviewClient.getAverageRatingForCompany(reviewMessage.getCompanyId());
        company.setRating(averageRating);
        companyRepository.save(company);
    }
}
