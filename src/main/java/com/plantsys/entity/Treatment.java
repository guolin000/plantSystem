package com.plantsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName treatment
 */
@TableName(value ="treatment")
@Data
public class Treatment implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer treatmentId;

    /**
     * 
     */
    private Integer medicamentId;

    /**
     * 
     */
    private Integer creator;

    /**
     * 
     */
    private String createTime;

    /**
     * 
     */
    private String updateTime;

    /**
     * 
     */
    private Integer userId;

    /**
     * 0 正在进行中 1已结束
     */
    private Integer status;

    /**
     * plant
     */
    private Integer plantId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}