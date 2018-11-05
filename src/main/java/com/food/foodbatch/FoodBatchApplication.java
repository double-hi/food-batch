package com.food.foodbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableBatchProcessing
@MapperScan(basePackages = {"com.food.foodbatch.dao.**"})
public class FoodBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodBatchApplication.class, args);
    }
}
