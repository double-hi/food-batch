package com.food.foodbatch.model;

import lombok.Data;

@Data
public class GovSouEntityVo {

    private String dietProviderId;

    private String dietProviderName;

    private String validityFrom;

    private String validityTo;

    private Integer mobileInspectCount;

    private String permitNo;

    private String dietProviderAddr;

    private String director;
}
