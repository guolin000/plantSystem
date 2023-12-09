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
        PlantInfo other = (PlantInfo) that;
        return (this.getPlantId() == null ? other.getPlantId() == null : this.getPlantId().equals(other.getPlantId()))
            && (this.getPlantName() == null ? other.getPlantName() == null : this.getPlantName().equals(other.getPlantName()))
            && (this.getDiseaseName() == null ? other.getDiseaseName() == null : this.getDiseaseName().equals(other.getDiseaseName()))
            && (this.getSpeciesName() == null ? other.getSpeciesName() == null : this.getSpeciesName().equals(other.getSpeciesName()))
            && (this.getGenusName() == null ? other.getGenusName() == null : this.getGenusName().equals(other.getGenusName()))
            && (this.getFamilyName() == null ? other.getFamilyName() == null : this.getFamilyName().equals(other.getFamilyName()))
            && (this.getAlias() == null ? other.getAlias() == null : this.getAlias().equals(other.getAlias()))
            && (this.getFeature() == null ? other.getFeature() == null : this.getFeature().equals(other.getFeature()))
            && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()))
            && (this.getPoint() == null ? other.getPoint() == null : this.getPoint().equals(other.getPoint()))
            && (this.getTreatMethod() == null ? other.getTreatMethod() == null : this.getTreatMethod().equals(other.getTreatMethod()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPlantId() == null) ? 0 : getPlantId().hashCode());
        result = prime * result + ((getPlantName() == null) ? 0 : getPlantName().hashCode());
        result = prime * result + ((getDiseaseName() == null) ? 0 : getDiseaseName().hashCode());
        result = prime * result + ((getSpeciesName() == null) ? 0 : getSpeciesName().hashCode());
        result = prime * result + ((getGenusName() == null) ? 0 : getGenusName().hashCode());
        result = prime * result + ((getFamilyName() == null) ? 0 : getFamilyName().hashCode());
        result = prime * result + ((getAlias() == null) ? 0 : getAlias().hashCode());
        result = prime * result + ((getFeature() == null) ? 0 : getFeature().hashCode());
        result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
        result = prime * result + ((getPoint() == null) ? 0 : getPoint().hashCode());
        result = prime * result + ((getTreatMethod() == null) ? 0 : getTreatMethod().hashCode());
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
        sb.append(", diseaseName=").append(diseaseName);
        sb.append(", speciesName=").append(speciesName);
        sb.append(", genusName=").append(genusName);
        sb.append(", familyName=").append(familyName);
        sb.append(", alias=").append(alias);
        sb.append(", feature=").append(feature);
        sb.append(", value=").append(value);
        sb.append(", point=").append(point);
        sb.append(", treatMethod=").append(treatMethod);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}