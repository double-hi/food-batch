package com.food.foodbatch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * @program: food-batch
 * @description: 委内数据同步JOB预处理
 * @author: fhan
 * @create: 2018-11-05 09:54
 **/
public class GovSouListener implements JobExecutionListener {
    private final static Logger LOG = LoggerFactory.getLogger(GovSouListener.class);

    long startTime;
    long endTime;
    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = System.currentTimeMillis();
        LOG.info("任务开始");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        endTime = System.currentTimeMillis();
        LOG.info("任务处理结束");
        LOG.info("耗时:" + (endTime - startTime) + "ms");
    }

}
