package com.food.foodbatch;

import com.food.foodbatch.listener.GovSouListener;
import com.food.foodbatch.model.GovSouEntityVo;
import com.food.foodbatch.model.GovernmentSourceVo;
import com.food.foodbatch.processor.GovSouProcessor;
import com.food.foodbatch.reader.DataValidator;
import com.food.foodbatch.reader.GovSouReader;
import com.food.foodbatch.service.GovSouService;
import com.food.foodbatch.writer.GovSouWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.List;

/**
 * @program: food-batch
 * @description: JOB配置
 * @author: fhan
 * @create: 2018-11-05 09:57
 **/
@Configuration
public class GovSouConfig {

    @Value("${data.governmentUrl}")
    private String governmentUrl;

    @Value("${data.urlUserId}")
    private String urlUserId;

    @Bean
    public ItemReader<GovernmentSourceVo> reader() throws Exception {
        return new GovSouReader(governmentUrl, urlUserId);
    }

    @Bean
    public ItemProcessor<GovernmentSourceVo, List<GovSouEntityVo>> processor() {
        return new GovSouProcessor();
    }

    @Bean
    public ItemWriter<List<GovSouEntityVo>> writer(GovSouService govSouService) {//Spring能让容器中已有的Bean以参数的形式注入，Spring boot已经定义了DataSource
        return new GovSouWriter(govSouService);
    }

    @Bean
    public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager)
            throws Exception {
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setDatabaseType("oracle");
        return jobRepositoryFactoryBean.getObject();
    }

    @Bean
    public SimpleJobLauncher jobLauncher(DataSource dataSource, PlatformTransactionManager transactionManager)
            throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository(dataSource, transactionManager));
        return jobLauncher;
    }

    @Bean
    public Job importJob(JobBuilderFactory jobs, Step s1) {
        return jobs.get("importJob")
                .incrementer(new RunIdIncrementer())
                .flow(s1) //指定step
                .end()
                .listener(govSouListener()) //绑定监听器
                .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<GovernmentSourceVo> reader, ItemWriter<List<GovSouEntityVo>> writer,
                      ItemProcessor<GovernmentSourceVo,List<GovSouEntityVo>> processor) {
        return stepBuilderFactory
                .get("step1")
                .<GovernmentSourceVo,List<GovSouEntityVo>>chunk(5000) //批处理每次提交65000条数据
                .reader(reader) //给step绑定reader
                .processor(processor) //给step绑定Processor
                .writer(writer) //给step绑定writer
                .build();
    }

    @Bean
    public GovSouListener govSouListener() {
        return new GovSouListener();
    }

}
