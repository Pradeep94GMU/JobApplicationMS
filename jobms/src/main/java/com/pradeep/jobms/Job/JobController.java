package com.pradeep.jobms.Job;

import com.pradeep.jobms.dto.JobWithCompanyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    //it will be only handling the part to take request and call the service
    private JobService jobService;

    @Autowired
    public JobController(JobService jobService){
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobWithCompanyDTO>> findAll(){
        List<JobWithCompanyDTO> jobList =  jobService.findAll();
        if(jobList != null){
            return new ResponseEntity<>(jobList, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody Job job){
        //pass to service to crete this job
         jobService.createJob(job);
         return new ResponseEntity<>("New Job Created..",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findJobById(@PathVariable Long id){
        //have consistence response so use responseEntity
        Job job = jobService.findJobById(id);

        if(job != null){
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);

    }

    //delete by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        boolean res = jobService.deleteJob(id);
        if(res){
            return new ResponseEntity<>("Job has been deleted", HttpStatus.OK);
        }
        else{
            return new ResponseEntity("No ID Found", HttpStatus.NOT_FOUND);
        }

    }

    //update Job info

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job job){
        boolean updatedJob = jobService.updateJob(id, job);

        if(updatedJob){
            return new ResponseEntity<>("Job has been updated", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }



}
