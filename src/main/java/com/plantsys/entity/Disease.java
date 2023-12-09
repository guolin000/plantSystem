package com.plantsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName disease
 */
@TableName(value ="disease")
@Data
public class Disease implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer diseaseId;

    /**
     * 
     */
    private String diseaseName;

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
        Disease other = (Disease) that;
        return (this.getDiseaseId() == null ? other.getDiseaseId() == null : this.getDiseaseId().equals(other.getDiseaseId()))
            && (this.getDiseaseName() == null ? other.getDiseaseName() == null : this.getDiseaseName().equals(other.getDiseaseName()))
            && (this.getTreatMethod() == null ? other.getTreatMethod() == null : this.getTreatMethod().equals(other.getTreatMethod()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDiseaseId() == null) ? 0 : getDiseaseId().hashCode());
        result = prime * result + ((getDiseaseName() == null) ? 0 : getDiseaseName().hashCode());
        result = prime * result + ((getTreatMethod() == null) ? 0 : getTreatMethod().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", diseaseId=").append(diseaseId);
        sb.append(", diseaseName=").append(diseaseName);
        sb.append(", treatMethod=").append(treatMethod);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}