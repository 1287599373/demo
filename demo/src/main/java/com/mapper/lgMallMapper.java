package com.mapper;

import com.model.lgMall;

public interface lgMallMapper {
    int deleteByPrimaryKey(Integer mallid);

    int insert(lgMall record);

    int insertSelective(lgMall record);

    lgMall selectByPrimaryKey(Integer mallid);

    int updateByPrimaryKeySelective(lgMall record);

    int updateByPrimaryKey(lgMall record);
}