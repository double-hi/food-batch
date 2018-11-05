package com.food.foodbatch.reader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.food.foodbatch.listener.GovSouListener;
import com.food.foodbatch.model.GovernmentSourceVo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @program: food-batch
 * @description: 实现API调取数据Reader类
 * @author: fhan
 * @create: 2018-11-05 11:23
 **/
public class GovSouReader implements ItemReader<GovernmentSourceVo> {

    private final static Logger LOG = LoggerFactory.getLogger(GovSouListener.class);

    private final String governmentUrl;

    private static int pageNumber = 1;

    private final String urlUserId;

    private static int totalPage;

    public GovSouReader(String governmentUrl,String urlUserId) {
        this.governmentUrl = governmentUrl;
        this.urlUserId = urlUserId;
    }

    @Override
    public GovernmentSourceVo read() throws Exception {
        if(Objects.equals(pageNumber,1)){
            GovernmentSourceVo sourceVo = apiGetData(pageNumber ++);
            totalPage = sourceVo.getData().getPageInfo().getTotalPage();
            return sourceVo;
        }

        if (totalPage >= pageNumber) {
            return apiGetData(pageNumber ++);
        } else {
            return null;
        }
    }

    public GovernmentSourceVo apiGetData(int pageNumber) throws Exception{
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Request firstGet = new Request.Builder()
                .url(governmentUrl+"?userId='"+urlUserId+"'&pageNumber="+pageNumber)
                .get()
                .build();
        String jsonVO ;
        try {
            jsonVO = okHttpClient.newCall(firstGet).execute().body().string();
        }catch (IOException e1){
            LOG.error("第 1 次请求（ "+pageNumber+" ）失败。");
            try{
                jsonVO = okHttpClient.newCall(firstGet).execute().body().string();
            }catch (IOException e2){
                LOG.error("第 2 次请求（ "+pageNumber+" ）失败。");
                jsonVO = okHttpClient.newCall(firstGet).execute().body().string();
            }
        }
        return JSON.parseObject(jsonVO.substring(0, jsonVO.lastIndexOf("}")+1), new TypeReference<GovernmentSourceVo>() {});
    }
}

