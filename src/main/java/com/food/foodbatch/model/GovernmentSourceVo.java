package com.food.foodbatch.model;

import lombok.Data;

@Data
public class GovernmentSourceVo {

    private String success;

    private String message;

    private String code;

    private GovSouDataVo data;

}
