package com.plantsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName family_plant_count
 */
@TableName(value ="family_plant_count")
@Data
public class FamilyPlantCount implements Serializable {
    /**
     * 
     */
    private Integer familyId;

    /**
     * 
     */
    private String familyName;

    /**
     * 
     */
    private Long plantNum;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}