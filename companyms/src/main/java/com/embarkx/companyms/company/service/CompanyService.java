package com.embarkx.companyms.company.service;



import com.embarkx.companyms.company.dto.ReviewMessage;
import com.embarkx.companyms.company.entity.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();
    Company createCompany(Company company);
    Company getSingleCompany(Long companyId);
    void deleteCompany(Long companyId);
    Company updateCompany(Long companyId,Company company);
    void updateCompanyRating(ReviewMessage reviewMessage);
}
