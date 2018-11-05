package com.food.foodbatch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: food-batch
 * @description: JobApi入口
 * @author: fhan
 * @create: 2018-11-05 10:17
 **/

@RestController
public class GovSouController {

    @Autowired
    SimpleJobLauncher jobLauncher;

    @Autowired
    Job importJob;

    public JobParameters jobParameters;

    @RequestMapping("/test")
    public void imp() throws  Exception{
        jobParameters = new JobParametersBuilder()
                .addLong("time",System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(importJob,jobParameters);
    }
}
