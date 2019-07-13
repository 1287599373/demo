package com.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.model.LgTransactionDetails;

public interface LgTransactionDetailsMapper {
    int deleteByPrimaryKey(Integer tdid);

    int insert(LgTransactionDetails record);

    int insertSelective(LgTransactionDetails record);

    LgTransactionDetails selectByPrimaryKey(Integer tdid);

    int updateByPrimaryKeySelective(LgTransactionDetails record);

    int updateByPrimaryKey(LgTransactionDetails record);
    

    List<Map<String, Object>> getMallTradePage(@Param("startTime") String startTime,@Param("endTime") String endTime,
    			@Param("userName") String userName,@Param("mallOrder") String mallOrder,@Param("mallName") String mallName);

	List<Map<String, String>> getAllMallTrade(@Param("startTime") String startTime,@Param("endTime") String endTime,
			@Param("userName") String userName,@Param("mallOrder") String mallOrder,@Param("mallName") String mallName);
}