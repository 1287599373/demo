package com.mapper;

import java.util.List;

import com.model.LgMall;

public interface LgMallMapper {
    int deleteByPrimaryKey(Integer mallid);

    int insert(LgMall record);

    int insertSelective(LgMall record);

    LgMall selectByPrimaryKey(Integer mallid);

    int updateByPrimaryKeySelective(LgMall record);

    int updateByPrimaryKey(LgMall record);

    List<LgMall> findMalls(LgMall mall);
}