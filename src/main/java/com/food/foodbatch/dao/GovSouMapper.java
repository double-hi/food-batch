package com.food.foodbatch.dao;

import com.food.foodbatch.dao.sql.GovSouSqlProvider;
import com.food.foodbatch.model.GovSouEntityVo;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.UpdateProvider;


public interface GovSouMapper {

    @InsertProvider(type = GovSouSqlProvider.class, method = "insertTPermit")
    void insertTPermit(GovSouEntityVo permitInfo);

    @UpdateProvider(type = GovSouSqlProvider.class, method = "updateTPermit")
    Integer updateTPermit(GovSouEntityVo permitInfo);

    @UpdateProvider(type = GovSouSqlProvider.class, method = "flagFailure")
    Integer flagFailure();

}
