package com.plantsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName medicament
 */
@TableName(value ="medicament")
@Data
public class Medicament implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
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
    private Integer diseaseId;

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
        Medicament other = (Medicament) that;
        return (this.getMedicamentId() == null ? other.getMedicamentId() == null : this.getMedicamentId().equals(other.getMedicamentId()))
            && (this.getMedicamentName() == null ? other.getMedicamentName() == null : this.getMedicamentName().equals(other.getMedicamentName()))
            && (this.getDosage() == null ? other.getDosage() == null : this.getDosage().equals(other.getDosage()))
            && (this.getDuration() == null ? other.getDuration() == null : this.getDuration().equals(other.getDuration()))
            && (this.getDiseaseId() == null ? other.getDiseaseId() == null : this.getDiseaseId().equals(other.getDiseaseId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMedicamentId() == null) ? 0 : getMedicamentId().hashCode());
        result = prime * result + ((getMedicamentName() == null) ? 0 : getMedicamentName().hashCode());
        result = prime * result + ((getDosage() == null) ? 0 : getDosage().hashCode());
        result = prime * result + ((getDuration() == null) ? 0 : getDuration().hashCode());
        result = prime * result + ((getDiseaseId() == null) ? 0 : getDiseaseId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", medicamentId=").append(medicamentId);
        sb.append(", medicamentName=").append(medicamentName);
        sb.append(", dosage=").append(dosage);
        sb.append(", duration=").append(duration);
        sb.append(", diseaseId=").append(diseaseId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}