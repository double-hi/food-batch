package com.food.foodbatch.reader;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.InitializingBean;


/**
 * @program: food-batch
 * @description: 输入数据校验
 * @author: fhan
 * @create: 2018-11-05 09:42
 **/
public class DataValidator<T> implements Validator<T>, InitializingBean {
     @Override
     public void afterPropertiesSet() throws Exception { //使用JSR-303的Validator来校验我们的数据，在此处进行JSR-303的Validator的初始化

            }
             @Override
    public void validate(T value) throws ValidationException {

            }
}