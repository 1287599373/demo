package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	User login(@Param(value = "username") String username, @Param(value="password") String password);
	
	List<User> findAllUsers();
}