package com.pradeep.jobms.dto;

import com.pradeep.jobms.Job.Job;
import com.pradeep.jobms.external.Company;

public class JobWithCompanyDTO {
    //client need job and which company has this job

    private Job job;
    private Long companyId;

    private String companyName;
    private String Description;;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
