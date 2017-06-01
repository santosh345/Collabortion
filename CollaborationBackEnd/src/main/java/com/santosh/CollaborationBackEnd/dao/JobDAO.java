package com.santosh.CollaborationBackEnd.dao;

import java.util.List;

import com.santosh.CollaborationBackEnd.model.Job;
import com.santosh.CollaborationBackEnd.model.JobApplication;

public interface JobDAO {
	public List<Job> getAllOpenedJobs();

	public Job getJobDetails(Long id);

	public boolean updateJob(Job job);

	public boolean updateJob(JobApplication jobapplicaton);

	public boolean save(JobApplication jobapplication);

	public boolean save(Job job);

	public List<Job> getMyAppliedjobs(String userID);

	public JobApplication getJobApplication(String userID,Long jobID);

	public JobApplication getJobApplication(Long id);

}
