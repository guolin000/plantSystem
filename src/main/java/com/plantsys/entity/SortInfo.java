package com.plantsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sort_info
 */
@TableName(value ="sort_info")
@Data
public class SortInfo implements Serializable {
    /**
     * 
     */
    @TableField(value = "plant_id")
    private Integer plantId;

    /**
     * 
     */
    @TableField(value = "plant_name")
    private String plantName;

    /**
     * 
     */
    @TableField(value = "family_name")
    private String familyName;

    /**
     * 
     */
    @TableField(value = "genus_name")
    private String genusName;

    /**
     * 
     */
    @TableField(value = "species_name")
    private String speciesName;

    /**
     * 
     */
    @TableField(value = "province_name")
    private String provinceName;

    /**
     * 
     */
    @TableField(value = "city_name")
    private String cityName;

    /**
     * 
     */
    @TableField(value = "county_name")
    private String countyName;

    /**
     * 
     */
    @TableField(value = "environment")
    private String environment;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SortInfo other = (SortInfo) that;
        return (this.getPlantId() == null ? other.getPlantId() == null : this.getPlantId().equals(other.getPlantId()))
            && (this.getPlantName() == null ? other.getPlantName() == null : this.getPlantName().equals(other.getPlantName()))
            && (this.getFamilyName() == null ? other.getFamilyName() == null : this.getFamilyName().equals(other.getFamilyName()))
            && (this.getGenusName() == null ? other.getGenusName() == null : this.getGenusName().equals(other.getGenusName()))
            && (this.getSpeciesName() == null ? other.getSpeciesName() == null : this.getSpeciesName().equals(other.getSpeciesName()))
            && (this.getProvinceName() == null ? other.getProvinceName() == null : this.getProvinceName().equals(other.getProvinceName()))
            && (this.getCityName() == null ? other.getCityName() == null : this.getCityName().equals(other.getCityName()))
            && (this.getCountyName() == null ? other.getCountyName() == null : this.getCountyName().equals(other.getCountyName()))
            && (this.getEnvironment() == null ? other.getEnvironment() == null : this.getEnvironment().equals(other.getEnvironment()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPlantId() == null) ? 0 : getPlantId().hashCode());
        result = prime * result + ((getPlantName() == null) ? 0 : getPlantName().hashCode());
        result = prime * result + ((getFamilyName() == null) ? 0 : getFamilyName().hashCode());
        result = prime * result + ((getGenusName() == null) ? 0 : getGenusName().hashCode());
        result = prime * result + ((getSpeciesName() == null) ? 0 : getSpeciesName().hashCode());
        result = prime * result + ((getProvinceName() == null) ? 0 : getProvinceName().hashCode());
        result = prime * result + ((getCityName() == null) ? 0 : getCityName().hashCode());
        result = prime * result + ((getCountyName() == null) ? 0 : getCountyName().hashCode());
        result = prime * result + ((getEnvironment() == null) ? 0 : getEnvironment().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", plantId=").append(plantId);
        sb.append(", plantName=").append(plantName);
        sb.append(", familyName=").append(familyName);
        sb.append(", genusName=").append(genusName);
        sb.append(", speciesName=").append(speciesName);
        sb.append(", provinceName=").append(provinceName);
        sb.append(", cityName=").append(cityName);
        sb.append(", countyName=").append(countyName);
        sb.append(", environment=").append(environment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}