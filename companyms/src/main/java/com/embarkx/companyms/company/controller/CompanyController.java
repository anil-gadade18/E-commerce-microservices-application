package com.embarkx.companyms.company.controller;


import com.embarkx.companyms.company.entity.Company;
import com.embarkx.companyms.company.exception.CompanyNotFoundException;
import com.embarkx.companyms.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
           return new ResponseEntity<>(this.companyService.getAllCompanies(), HttpStatus.OK);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<Company> getSingleCompany(@PathVariable Long companyId){
        return new ResponseEntity<>(this.companyService.getSingleCompany(companyId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company){
        return new ResponseEntity<>(this.companyService.createCompany(company),HttpStatus.CREATED);
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company,
                                                 @PathVariable Long companyId){
        Company updatedCompany=this.companyService.updateCompany(companyId,company);
        if(updatedCompany!=null){
            return new ResponseEntity<>(updatedCompany,HttpStatus.OK);
        }else{
            throw new CompanyNotFoundException("Company Not Found to update : "+companyId);
        }
    }


    @DeleteMapping("/{companyId}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long companyId){
        this.companyService.deleteCompany(companyId);
        return new ResponseEntity<>("Company deleted successfully with an id : "+companyId,HttpStatus.OK);
    }

}
