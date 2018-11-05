package com.food.foodbatch.service;

import com.food.foodbatch.model.GovSouEntityVo;

import java.util.List;

public interface GovSouService {

    void batchReplaceTPermit(List<GovSouEntityVo> permitInfos);

    Integer flagFailure();

}
