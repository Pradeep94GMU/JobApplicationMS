package com.pradeep.companyms.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServImpl implements CompanyService{

    @Autowired
    CompanyRepo companyRepo;

    //depended on repo, now we can access all the methods
    public CompanyServImpl(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }

    @Override
    public List<Company> getAllCompanies() {
        List<Company> companyList = companyRepo.findAll();
        return companyList;

    }

    @Override
    public boolean createCompany(Company company) {
        Company createdCompany = companyRepo.save(company);
        return true;
    }

    @Override
    public boolean deleteCompany(Long id) {

        Company company = companyRepo.findById(id).orElse(null);

        if(company != null){
            companyRepo.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public boolean updateCompany(Long id, Company company) {

        Company oldCompany = companyRepo.findById(id).orElse(null);
        if(oldCompany!= null){
            //we need data for new company
            oldCompany.setAddress(company.getAddress());
            oldCompany.setDepartment(company.getDepartment());
            oldCompany.setDescription(company.getDescription());
            oldCompany.setName(company.getName());
            oldCompany.setRevenue(company.getRevenue());
            oldCompany.setNumberOfEmployees(company.getNumberOfEmployees());
            oldCompany.setJobId(company.getJobId());
            oldCompany.setReviewId(company.getReviewId());
            companyRepo.save(oldCompany);
            return true;
        }
        return false;
    }

    @Override
    public Company getCompanyById(Long id) {

        return companyRepo.findById(id).orElse(null);

    }
}
