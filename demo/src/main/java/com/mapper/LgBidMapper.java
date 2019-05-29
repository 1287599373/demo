package com.mapper;

import com.model.LgBid;

public interface LgBidMapper {
    int deleteByPrimaryKey(Integer bidid);

    int insert(LgBid record);

    int insertSelective(LgBid record);

    LgBid selectByPrimaryKey(Integer bidid);

    int updateByPrimaryKeySelective(LgBid record);

    int updateByPrimaryKey(LgBid record);
}