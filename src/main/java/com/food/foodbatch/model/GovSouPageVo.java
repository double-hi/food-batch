package com.food.foodbatch.model;

import lombok.Data;

@Data
public class GovSouPageVo {

    private Integer totalCount;

    private Integer totalPage;

    private Integer pageSize;

    private Integer pageNumber;

    private Integer hasPreviousPage;

    private Integer hasNextPage;

}
