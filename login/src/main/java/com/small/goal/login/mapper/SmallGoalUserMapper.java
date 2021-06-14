package com.small.goal.login.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;


/**
 * Description: 用户表mapper
 * Created on 2020年09月24日
 *
 * @author 念着倒才子傻
 */
@Mapper
public interface SmallGoalUserMapper {

    String selectTest(@Param("id") String id);
}
