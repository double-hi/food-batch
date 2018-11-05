package com.food.foodbatch.model;

import lombok.Data;

import java.util.List;

@Data
public class GovSouDataVo {

    private GovSouPageVo pageInfo;

    private List<GovSouEntityVo> items;
}
