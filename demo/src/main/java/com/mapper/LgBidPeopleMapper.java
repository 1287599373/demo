package com.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.model.LgBidPeople;

public interface LgBidPeopleMapper {
    int deleteByPrimaryKey(Integer bidpeopleid);

    int insert(LgBidPeople record);

    int insertSelective(LgBidPeople record);

    LgBidPeople selectByPrimaryKey(Integer bidpeopleid);

    int updateByPrimaryKeySelective(LgBidPeople record);

    int updateByPrimaryKey(LgBidPeople record);

	List<Map<String, Object>> getLgBidPeoplePage(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("userName") String userName);
}