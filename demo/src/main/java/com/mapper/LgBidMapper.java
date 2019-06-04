package com.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.LgBid;

public interface LgBidMapper {
    int deleteByPrimaryKey(Integer bidid);

    int insert(LgBid record);

    int insertSelective(LgBid record);

    LgBid selectByPrimaryKey(Integer bidid);

    int updateByPrimaryKeySelective(LgBid record);

    int updateByPrimaryKey(LgBid record);

	List<Map<String, Object>> getLgBidPage(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("userName") String userName);
}