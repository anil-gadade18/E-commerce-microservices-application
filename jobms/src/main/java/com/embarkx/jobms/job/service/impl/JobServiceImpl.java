package com.embarkx.jobms.job.service.impl;


import com.embarkx.jobms.job.clients.CompanyClient;
import com.embarkx.jobms.job.clients.ReviewClient;
import com.embarkx.jobms.job.dto.JobDto;
import com.embarkx.jobms.job.entity.Job;
import com.embarkx.jobms.job.exception.JobNotFoundException;
import com.embarkx.jobms.job.external.Company;
import com.embarkx.jobms.job.external.Review;
import com.embarkx.jobms.job.mapper.JobMapper;
import com.embarkx.jobms.job.repository.JobRepository;
import com.embarkx.jobms.job.service.JobService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired private JobRepository jobRepository;
    @Autowired private RestTemplate restTemplate;
    @Autowired private CompanyClient companyClient;
    @Autowired private ReviewClient reviewClient;

    int attempt = 0;

    @Override
   // @CircuitBreaker(name="companyBreaker",fallbackMethod = "companyBreakerFallback")
   // @Retry(name="companyBreaker",fallbackMethod = "companyBreakerFallback")
    @RateLimiter(name="companyBreaker",fallbackMethod = "companyBreakerFallback")
    public List<JobDto> getAllJobs() {

        System.out.println("Attempt : "+ ++attempt);
        List<Job> jobs = jobRepository.findAll();
        List<JobDto> jobWithCompanyDTOs = new ArrayList<>();
//        RestTemplate restTemplate = new RestTemplate();

//
//        for(Job job : jobs){
//            JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
//            jobWithCompanyDTO.setJob(job);
//
//            Company company = restTemplate.getForObject(
//                    "http://localhost:8081/companies/" + job.getCompanyId(),Company.class);
//            jobWithCompanyDTO.setCompany(company);
//            jobWithCompanyDTOs.add(jobWithCompanyDTO);
//        }
        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
    }


    public List<String> companyBreakerFallback(Exception e){
        List<String> list = new ArrayList<>();
        list.add("dummy");
        return list;
    }



    private JobDto convertToDto(Job job){

            //Fetching Company API

            Company company = companyClient.getCompany(job.getCompanyId());

            //Fetching Review API

           List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

        //  jobDto.setCompany(company);

            return JobMapper.mapToJobDto(job,company,reviews);
    }
    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public JobDto getSingleJob(Long jobId) {
        Job job = this.jobRepository.findById(jobId)
                .orElseThrow(()-> new JobNotFoundException("Job Not Found : "+jobId));
        return convertToDto(job);
    }


    @Override
    public void deleteJob(Long jobId) {
        Job job = this.jobRepository.findById(jobId).orElseThrow(()-> new JobNotFoundException("Job Not Found : "+jobId));
        this.jobRepository.delete(job);
    }

    @Override
    public Job updateJob(Long jobId, Job job) {
        Job existingJob = this.jobRepository.findById(jobId).orElseThrow(()-> new JobNotFoundException("Job Not Found : "+jobId));
        existingJob.setTitle(job.getTitle());
        existingJob.setDescription(job.getDescription());
        existingJob.setMinSalary(job.getMinSalary());
        existingJob.setMaxSalary(job.getMaxSalary());
        existingJob.setLocation(job.getLocation());
        return this.jobRepository.save(existingJob);
    }


//
//    private static List<Job> jobs = new ArrayList<>();
//    public static Long count= 0L;
//
//    static{
//        jobs.add(new Job(++count,"Software Engineer","I build software application","45000","60000","Pune"));
//        jobs.add(new Job(++count,"CyberSecurity Engineer","I Secure application","50000","80000","Pune"));
//        jobs.add(new Job(++count,"Network Engineer","I am Network Administrator","45000","60000","Pune"));
//    }
//
//    @Override
//    public List<Job> findAll() {
//        return jobs;
//    }
//
//    @Override
//    public void createJob(Job job) {
//        job.setId(++count);
//        jobs.add(job);
//    }
//
//    @Override
//    public Job getJobById(Long id) {
//        for (Job job : jobs){
//            if(job.getId().equals(id)){
//                return job;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public void deleteJobById(Long id) {
//        for(Job job : jobs){
//            if(job.getId().equals(id)){
//                jobs.remove(id);
//            }
//        }
//    }
}
