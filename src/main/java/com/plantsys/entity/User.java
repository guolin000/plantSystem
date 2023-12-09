package com.plantsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@Data
@TableName("user")
public class User implements Serializable {
    /**
     * TableId:主键注解
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String loginName;



    private String password;

    private Integer rid;


}
