package com.food.foodbatch.processor;

import com.food.foodbatch.model.GovSouDataVo;
import com.food.foodbatch.model.GovSouEntityVo;
import com.food.foodbatch.model.GovernmentSourceVo;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;

import java.util.List;

/**
 * @program: food-batch
 * @description: 数据处理类
 * @author: fhan
 * @create: 2018-11-05 09:46
 **/
public class GovSouProcessor implements ItemProcessor<GovernmentSourceVo,List<GovSouEntityVo>> {

    @Override
    public List<GovSouEntityVo> process(GovernmentSourceVo vo) throws Exception {
        return vo.getData().getItems();
    }

}
