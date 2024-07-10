package com.pradeep.companyms.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    //get company list
    @GetMapping
    public ResponseEntity<List<Company>> getCompanies(){
        List<Company> list =  companyService.getAllCompanies();
        if(list != null){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //create a company detail
    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        boolean created = companyService.createCompany(company);
        if(created){
            return new ResponseEntity<>("New company is created..", HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //getById
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company = companyService.getCompanyById(id);

        if(company!= null){
            return new ResponseEntity<>(company, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //put
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company){
        boolean updatedCompany = companyService.updateCompany(id, company);

        if(updatedCompany){
            return new ResponseEntity<>("Compaany has been updated", HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        boolean deletedCompany = companyService.deleteCompany(id);

        if(deletedCompany){
            return new ResponseEntity<>("Company is deleted..", HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
