package com.plantsys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName plant_info
 */
@TableName(value ="plant_info")
@Data
public class PlantInfo implements Serializable {
    /**
     * 
     */
    private Integer plantId;

    /**
     * 
     */
    private String plantName;

    /**
     * 
     */
    private String diseaseName;

    /**
     * 
     */
    private String speciesName;

    /**
     * 
     */
    private String genusName;

    /**
     * 
     */
    private String familyName;

    /**
     * 
     */
    private String alias;

    /**
     * 
     */
    private String feature;

    /**
     * 
     */
    private String value;

    /**
     * 
     */
    private String point;

    /**
     * 
     */
    private String treatMethod;
    private String speciesId;
    private String diseaseId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}