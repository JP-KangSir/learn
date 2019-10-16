package com.learning.myproject.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.learning.myproject.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao extends BaseMapper<User> {

  @Select("select * from user where username = #{username}")
  User getUserByUsername(String username);
}
