package com.embarkx.jobms.job.mapper;

import com.embarkx.jobms.job.dto.JobDto;
import com.embarkx.jobms.job.entity.Job;
import com.embarkx.jobms.job.external.Company;
import com.embarkx.jobms.job.external.Review;

import java.util.List;

public class JobMapper {

    public static JobDto mapToJobDto(Job job, Company company, List<Review> reviews){

        JobDto jobDto = new JobDto();
        jobDto.setId(job.getId());
        jobDto.setTitle(job.getTitle());
        jobDto.setDescription(job.getDescription());
        jobDto.setMinSalary(job.getMinSalary());
        jobDto.setMaxSalary(job.getMaxSalary());
        jobDto.setLocation(job.getLocation());
        jobDto.setCompany(company);
        jobDto.setReviews(reviews);
        return jobDto;
    }
}
