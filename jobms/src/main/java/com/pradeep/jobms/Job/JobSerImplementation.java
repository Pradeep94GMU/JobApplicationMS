package com.pradeep.jobms.Job;

import com.pradeep.jobms.dto.JobWithCompanyDTO;
import com.pradeep.jobms.external.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class JobSerImplementation implements JobService {

    private JobRepo jobRepo;


    @Autowired
    public JobSerImplementation(JobRepo jobRepo) {
        this.jobRepo = jobRepo;
    }

    @Override
    public List<JobWithCompanyDTO> findAll() {
        //our object is to give info of job and company to client
        List<JobWithCompanyDTO> jobWithCompanyDTOs = new ArrayList<>();

        List<Job> jobList = jobRepo.findAll();
        //in each job, find the companyId and use rest template to get the company detail
        RestTemplate restTemplate = new RestTemplate();
        for(Job job: jobList){
            Company company = restTemplate.getForObject(
                    "http://localhost:8081/company/" + job.getCompanyId(),
                    Company.class);
            //till now, we have job as company detail
            //so we need list of DTO
            JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
            jobWithCompanyDTO.setJob(job);
            jobWithCompanyDTO.setCompanyId(company.getJobId());
            jobWithCompanyDTO.setCompanyName(company.getName());
            jobWithCompanyDTO.setDescription(company.getDescription());


            //we have single job with single company
            jobWithCompanyDTOs.add(jobWithCompanyDTO);

        }

        return jobWithCompanyDTOs;
    }

    @Override
    public String createJob(Job job) {

        jobRepo.save(job);
        return "Job created successfully";
    }

    @Override
    public Job findJobById(Long id) {

        Job job =  jobRepo.findById(id).orElse(null);
        return job;
    }

    @Override
    public boolean updateJob(Long id, Job job) {
        //we need to find the Job with id first
        Optional<Job> jobOptional = jobRepo.findById(id);


        if(jobOptional.isPresent()){
            Job eachJob = jobOptional.get();
            eachJob.setDescription(job.getDescription());
            eachJob.setLocation(job.getLocation());
            eachJob.setTitle(job.getTitle());
            eachJob.setMinSalary(job.getMinSalary());
            eachJob.setMaxSalary(job.getMaxSalary());
            eachJob.setCompanyId(job.getCompanyId());
            jobRepo.save(eachJob);
            return true;
        }

        return false;
    }


    @Override
    public boolean deleteJob(Long id) {

        //before deleting, we need to find which is the job
        //using id we can find
        try{
            Job job = jobRepo.findById(id).orElse(null);
            jobRepo.delete(job);
            return true;
        }catch (Exception e){
            return false;
        }

        //or other way is: jobRepo.deleteByID(id);////
    }
}
