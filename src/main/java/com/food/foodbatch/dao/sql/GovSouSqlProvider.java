package com.food.foodbatch.dao.sql;

import com.food.foodbatch.model.GovSouEntityVo;

import java.util.Objects;

@SuppressWarnings("all")
public class GovSouSqlProvider {

    /**
     * 更新PERMIT_INFO数据，并设状态为0
     *
     * @param permitInfo
     * @return
     */
    public String updateTPermit(GovSouEntityVo permitInfo) {
        StringBuilder sql = new StringBuilder("update TB_PERMIT_INFO ");
        sql.append("     SET ");
        if(permitInfo.getDietProviderName() != null && !Objects.equals(permitInfo.getDietProviderName(),"") && !Objects.equals(permitInfo.getDietProviderName(),"null")){
            sql.append("            BUSI_NAME=q'[" + permitInfo.getDietProviderName() + "]',");
        }
        if(permitInfo.getDietProviderAddr() != null && !Objects.equals(permitInfo.getDietProviderAddr(),"") && !Objects.equals(permitInfo.getDietProviderAddr(),"null")){
            sql.append("            BUSI_ADDRESS=q'[" + permitInfo.getDietProviderAddr() + "]',");
        }
        if(permitInfo.getDirector() != null && !Objects.equals(permitInfo.getDirector(),"") && !Objects.equals(permitInfo.getDirector(),"null")){
            sql.append("            OWNER_NAME=q'[" + permitInfo.getDirector() + "]',");
        }
        if(permitInfo.getValidityTo() != null && !Objects.equals(permitInfo.getValidityTo(),"") && !Objects.equals(permitInfo.getValidityTo(),"null")){
            sql.append("            VALID_DATE=q'[" + permitInfo.getValidityTo() + "]',");
        }
        sql.append("            SOURCE_FLAG='1',");
        sql.append("            UPDATE_TIME=SYSDATE ");
        sql.append("    WHERE PERMIT_CODE = '" + permitInfo.getPermitNo() + "' ");

        return sql.toString();
    }

    public String insertTPermit(GovSouEntityVo permitInfo) {
        StringBuilder sql = new StringBuilder("insert into TB_PERMIT_INFO (");
        if(permitInfo.getDietProviderName() != null && !Objects.equals(permitInfo.getDietProviderName(),"") && !Objects.equals(permitInfo.getDietProviderName(),"null")){
            sql.append("            BUSI_NAME,");
        }
        sql.append("            PERMIT_CODE,");
        if(permitInfo.getDietProviderAddr() != null && !Objects.equals(permitInfo.getDietProviderAddr(),"") && !Objects.equals(permitInfo.getDietProviderAddr(),"null")){
            sql.append("            BUSI_ADDRESS,");
        }
        if(permitInfo.getDirector() != null && !Objects.equals(permitInfo.getDirector(),"") && !Objects.equals(permitInfo.getDirector(),"null")){
            sql.append("            OWNER_NAME,");
        }
        if(permitInfo.getValidityTo() != null && !Objects.equals(permitInfo.getValidityTo(),"") && !Objects.equals(permitInfo.getValidityTo(),"null")){
            sql.append("            VALID_DATE,");
        }
        sql.append("            SOURCE_FLAG,");
        sql.append("            UPDATE_TIME");
        sql.append("            ) VALUES (");
        if(permitInfo.getDietProviderName() != null && !Objects.equals(permitInfo.getDietProviderName(),"") && !Objects.equals(permitInfo.getDietProviderName(),"null")){
            sql.append("            q'[" + permitInfo.getDietProviderName() + "]',");
        }
        sql.append("            q'[" + permitInfo.getPermitNo() + "]',");
        if(permitInfo.getDietProviderAddr() != null && !Objects.equals(permitInfo.getDietProviderAddr(),"") && !Objects.equals(permitInfo.getDietProviderAddr(),"null")){
            sql.append("            q'[" + permitInfo.getDietProviderAddr() + "]',");
        }
        if(permitInfo.getDirector() != null && !Objects.equals(permitInfo.getDirector(),"") && !Objects.equals(permitInfo.getDirector(),"null")){
            sql.append("            q'[" + permitInfo.getDirector() + "]',");
        }
        if(permitInfo.getValidityTo() != null && !Objects.equals(permitInfo.getValidityTo(),"") && !Objects.equals(permitInfo.getValidityTo(),"null")){
            sql.append("            q'[" + permitInfo.getValidityTo() + "]',");
        }
        sql.append("            '1',");
        sql.append("            SYSDATE");
        sql.append("            )");

        return sql.toString();
    }

    /**
     * 设置失效商家
     *
     * @return
     */
    public String flagFailure() {
        StringBuilder sql = new StringBuilder("update TB_PERMIT_INFO ");
        sql.append("    SET SOURCE_FLAG = '2'");
        sql.append("    , UPDATE_TIME = SYSDATE");
        sql.append("    WHERE (UPDATE_TIME + interval '7' day) < SYSDATE AND SOURCE_FLAG != '2'");
        return sql.toString();
    }

}
