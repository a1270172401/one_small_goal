package com.small.goal.login.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * 用户表实体类
 * Created on 2020年09月24日
 *
 * @author 念着倒才子傻
 */
@Data
public class SmallGoalUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 创建时间
     */
    private java.util.Date creatTime;
    /**
     * 是否删除0否1是
     */
    private Integer isDelete;
}
