package com.pradeep.companyms.Company;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {

    List<Company> getAllCompanies();

    boolean createCompany(Company company);

    boolean deleteCompany(Long id);

    boolean updateCompany(Long id, Company company);

    Company getCompanyById(Long id);

}
