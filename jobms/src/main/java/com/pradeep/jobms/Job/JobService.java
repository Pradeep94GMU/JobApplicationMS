package com.pradeep.jobms.Job;

import com.pradeep.jobms.dto.JobWithCompanyDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobService {
    //only methods
    List<JobWithCompanyDTO> findAll();
    String createJob(Job job);

    Job findJobById(Long id);
    boolean updateJob(Long id, Job job);
    boolean deleteJob(Long id);
}
