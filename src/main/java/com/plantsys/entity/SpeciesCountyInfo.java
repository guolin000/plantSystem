package com.plantsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName species_county_info
 */
@TableName(value ="species_county_info")
@Data
public class SpeciesCountyInfo implements Serializable {
    /**
     * 
     */
    @TableField(value = "species_id")
    private Integer speciesId;

    /**
     * 
     */
    @TableField(value = "county_id")
    private Integer countyId;

    /**
     * 
     */
    @TableField(value = "species_name")
    private String speciesName;

    /**
     * 
     */
    @TableField(value = "county_name")
    private String countyName;

    /**
     * 
     */
    @TableField(value = "city_name")
    private String cityName;

    /**
     * 
     */
    @TableField(value = "province_name")
    private String provinceName;

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
        SpeciesCountyInfo other = (SpeciesCountyInfo) that;
        return (this.getSpeciesId() == null ? other.getSpeciesId() == null : this.getSpeciesId().equals(other.getSpeciesId()))
            && (this.getCountyId() == null ? other.getCountyId() == null : this.getCountyId().equals(other.getCountyId()))
            && (this.getSpeciesName() == null ? other.getSpeciesName() == null : this.getSpeciesName().equals(other.getSpeciesName()))
            && (this.getCountyName() == null ? other.getCountyName() == null : this.getCountyName().equals(other.getCountyName()))
            && (this.getCityName() == null ? other.getCityName() == null : this.getCityName().equals(other.getCityName()))
            && (this.getProvinceName() == null ? other.getProvinceName() == null : this.getProvinceName().equals(other.getProvinceName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSpeciesId() == null) ? 0 : getSpeciesId().hashCode());
        result = prime * result + ((getCountyId() == null) ? 0 : getCountyId().hashCode());
        result = prime * result + ((getSpeciesName() == null) ? 0 : getSpeciesName().hashCode());
        result = prime * result + ((getCountyName() == null) ? 0 : getCountyName().hashCode());
        result = prime * result + ((getCityName() == null) ? 0 : getCityName().hashCode());
        result = prime * result + ((getProvinceName() == null) ? 0 : getProvinceName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", speciesId=").append(speciesId);
        sb.append(", countyId=").append(countyId);
        sb.append(", speciesName=").append(speciesName);
        sb.append(", countyName=").append(countyName);
        sb.append(", cityName=").append(cityName);
        sb.append(", provinceName=").append(provinceName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}