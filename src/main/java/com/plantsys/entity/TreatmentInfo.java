package com.plantsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName treatment_info
 */
@TableName(value ="treatment_info")
@Data
public class TreatmentInfo implements Serializable {
    /**
     * 
     */
    private Integer treatmentId;

    /**
     * 
     */
    private String plantName;

    /**
     * 
     */
    private Integer diseaseId;

    /**
     * 
     */
    private String diseaseName;

    /**
     * 
     */
    private Integer medicamentId;

    /**
     * 
     */
    private String medicamentName;

    /**
     * 
     */
    private String dosage;

    /**
     * 
     */
    private String duration;

    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    private String createTime;

    /**
     * 
     */
    private String updateTime;

    /**
     * 0 正在进行中 1已结束
     */
    private Integer status;

    /**
     * plant
     */
    private Integer plantId;

    /**
     * 
     */
    private String loginName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}