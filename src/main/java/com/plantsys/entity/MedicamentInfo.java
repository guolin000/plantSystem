package com.plantsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName medicament_info
 */
@TableName(value ="medicament_info")
@Data
public class MedicamentInfo implements Serializable {
    /**
     * 
     */
    private Integer medicamentId;

    /**
     * 
     */
    private String medicamentName;

    /**
     * 药剂用量
     */
    private String dosage;

    /**
     * 作用期限
     */
    private String duration;

    /**
     * 
     */
    private Integer diseaseId;

    /**
     * 
     */
    private String diseaseName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}