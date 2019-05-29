package com.mapper;

import com.model.LgBidPeople;

public interface LgBidPeopleMapper {
    int deleteByPrimaryKey(Integer bidpeopleid);

    int insert(LgBidPeople record);

    int insertSelective(LgBidPeople record);

    LgBidPeople selectByPrimaryKey(Integer bidpeopleid);

    int updateByPrimaryKeySelective(LgBidPeople record);

    int updateByPrimaryKey(LgBidPeople record);
}