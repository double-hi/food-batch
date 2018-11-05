package com.food.foodbatch.writer;

import com.food.foodbatch.model.GovSouEntityVo;
import com.food.foodbatch.service.GovSouService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.batch.item.ItemWriter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: food-batch
 * @description: 实现数据同步Writer类
 * @author: fhan
 * @create: 2018-11-05 15:32
 **/
@NoArgsConstructor
@AllArgsConstructor
public class GovSouWriter implements ItemWriter<List<GovSouEntityVo>> {

    private GovSouService govSouService;

    @Override
    public void write(List<? extends List<GovSouEntityVo>> list) throws Exception {
        List<GovSouEntityVo> entitys = list.stream().flatMap(oneList -> oneList.stream()).collect(Collectors.toList());
        govSouService.batchReplaceTPermit(entitys);
    }
}
