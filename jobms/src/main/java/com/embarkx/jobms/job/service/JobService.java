package com.embarkx.jobms.job.service;



import com.embarkx.jobms.job.dto.JobDto;
import com.embarkx.jobms.job.entity.Job;

import java.util.List;

public interface JobService {
//
//    List<Job> findAll();
//    void createJob(Job job);
//    Job getJobById(Long id);
//    void deleteJobById(Long id);

    List<JobDto> getAllJobs();
    Job createJob(Job job);
    JobDto getSingleJob(Long jobId);
    void deleteJob(Long jobId);
    Job updateJob(Long jobId,Job job);

}
