package com.embarkx.jobms.job.controller;


import com.embarkx.jobms.job.dto.JobDto;
import com.embarkx.jobms.job.entity.Job;
import com.embarkx.jobms.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;


    @GetMapping
    public ResponseEntity<List<JobDto>> getAllJobs(){
        return new ResponseEntity<>(this.jobService.getAllJobs(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job){
        return new ResponseEntity<>(this.jobService.createJob(job),HttpStatus.CREATED);
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<JobDto> getSingleJob(@PathVariable Long jobId){
        return new ResponseEntity<>(this.jobService.getSingleJob(jobId),HttpStatus.OK);
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<String> deleteJob(@PathVariable Long jobId){
        this.jobService.deleteJob(jobId);
        return new ResponseEntity<>("Job has deleted Successfully with an ID : "+jobId,HttpStatus.OK);
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<Job> updateJob(@PathVariable Long jobId, @RequestBody Job job){
        return new ResponseEntity<>(this.jobService.updateJob(jobId,job),HttpStatus.OK);
    }







//    @GetMapping("/jobs")
//    public ResponseEntity<List<Job>> findAll(){
//        return new ResponseEntity<>(this.jobService.findAll(), HttpStatus.OK);
//    }
//
//    @PostMapping("/jobs")
//    public ResponseEntity<String> createJob(@RequestBody Job job){
//        this.jobService.createJob(job);
//        return ResponseEntity.status(HttpStatus.CREATED).body("Job is created successfully");
//    }
//
//    @GetMapping("/jobs/{id}")
//    public ResponseEntity<Job> getSingleJob(@PathVariable Long id){
//        Job job =  this.jobService.getJobById(id);
//        if(job!=null)
//            return new ResponseEntity<>(this.jobService.getJobById(id),HttpStatus.OK);
//        return new ResponseEntity<>(new Job(1000L,"dummy","dummy","76000","670000","pune"),HttpStatus.NOT_FOUND);
//
//    }
//
//    @DeleteMapping("/jobs/{id}")
//    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
//        this.jobService.deleteJobById(id);
//        return new ResponseEntity<>("Job has deleted successfully....",HttpStatus.OK);
//    }
}
