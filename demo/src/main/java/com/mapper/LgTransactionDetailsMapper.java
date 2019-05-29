package com.mapper;

import com.model.LgTransactionDetails;

public interface LgTransactionDetailsMapper {
    int deleteByPrimaryKey(Integer tdid);

    int insert(LgTransactionDetails record);

    int insertSelective(LgTransactionDetails record);

    LgTransactionDetails selectByPrimaryKey(Integer tdid);

    int updateByPrimaryKeySelective(LgTransactionDetails record);

    int updateByPrimaryKey(LgTransactionDetails record);
}