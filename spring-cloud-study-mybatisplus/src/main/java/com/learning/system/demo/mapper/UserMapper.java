package com.learning.system.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.learning.system.demo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User>{
	
}
