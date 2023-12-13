package com.plantsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName species_info
 */
@TableName(value ="species_info")
@Data
public class SpeciesInfo implements Serializable {
    /**
     * 
     */
    @TableField(value = "species_id")
    private Integer speciesId;

    /**
     * 
     */
    @TableField(value = "species_name")
    private String speciesName;

    /**
     * 
     */
    @TableField(value = "genus_name")
    private String genusName;

    /**
     * 
     */
    @TableField(value = "family_name")
    private String familyName;

    /**
     * 
     */
    @TableField(value = "genus_id")
    private Integer genusId;

    /**
     * 
     */
    @TableField(value = "family_id")
    private Integer familyId;

    /**
     * 
     */
    @TableField(value = "environment")
    private String environment;

    /**
     * 
     */
    @TableField(value = "feature")
    private String feature;

    /**
     * 
     */
    @TableField(value = "value")
    private String value;

    /**
     * 
     */
    @TableField(value = "point")
    private String point;

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
        SpeciesInfo other = (SpeciesInfo) that;
        return (this.getSpeciesId() == null ? other.getSpeciesId() == null : this.getSpeciesId().equals(other.getSpeciesId()))
            && (this.getSpeciesName() == null ? other.getSpeciesName() == null : this.getSpeciesName().equals(other.getSpeciesName()))
            && (this.getGenusName() == null ? other.getGenusName() == null : this.getGenusName().equals(other.getGenusName()))
            && (this.getFamilyName() == null ? other.getFamilyName() == null : this.getFamilyName().equals(other.getFamilyName()))
            && (this.getGenusId() == null ? other.getGenusId() == null : this.getGenusId().equals(other.getGenusId()))
            && (this.getFamilyId() == null ? other.getFamilyId() == null : this.getFamilyId().equals(other.getFamilyId()))
            && (this.getEnvironment() == null ? other.getEnvironment() == null : this.getEnvironment().equals(other.getEnvironment()))
            && (this.getFeature() == null ? other.getFeature() == null : this.getFeature().equals(other.getFeature()))
            && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()))
            && (this.getPoint() == null ? other.getPoint() == null : this.getPoint().equals(other.getPoint()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSpeciesId() == null) ? 0 : getSpeciesId().hashCode());
        result = prime * result + ((getSpeciesName() == null) ? 0 : getSpeciesName().hashCode());
        result = prime * result + ((getGenusName() == null) ? 0 : getGenusName().hashCode());
        result = prime * result + ((getFamilyName() == null) ? 0 : getFamilyName().hashCode());
        result = prime * result + ((getGenusId() == null) ? 0 : getGenusId().hashCode());
        result = prime * result + ((getFamilyId() == null) ? 0 : getFamilyId().hashCode());
        result = prime * result + ((getEnvironment() == null) ? 0 : getEnvironment().hashCode());
        result = prime * result + ((getFeature() == null) ? 0 : getFeature().hashCode());
        result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
        result = prime * result + ((getPoint() == null) ? 0 : getPoint().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", speciesId=").append(speciesId);
        sb.append(", speciesName=").append(speciesName);
        sb.append(", genusName=").append(genusName);
        sb.append(", familyName=").append(familyName);
        sb.append(", genusId=").append(genusId);
        sb.append(", familyId=").append(familyId);
        sb.append(", environment=").append(environment);
        sb.append(", feature=").append(feature);
        sb.append(", value=").append(value);
        sb.append(", point=").append(point);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}