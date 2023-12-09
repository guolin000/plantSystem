package com.plantsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色表
 */
@Data
@TableName("role")
public class Role {
    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;

    private String roleName;

}
