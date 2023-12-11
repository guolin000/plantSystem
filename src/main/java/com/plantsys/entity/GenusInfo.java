package com.plantsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName genus_info
 */
@TableName(value ="genus_info")
@Data
public class GenusInfo implements Serializable {
    /**
     * 
     */
    @TableField(value = "genus_id")
    private Integer genusId;

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
    @TableField(value = "family_id")
    private Integer familyId;

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
        GenusInfo other = (GenusInfo) that;
        return (this.getGenusId() == null ? other.getGenusId() == null : this.getGenusId().equals(other.getGenusId()))
            && (this.getGenusName() == null ? other.getGenusName() == null : this.getGenusName().equals(other.getGenusName()))
            && (this.getFamilyName() == null ? other.getFamilyName() == null : this.getFamilyName().equals(other.getFamilyName()))
            && (this.getFamilyId() == null ? other.getFamilyId() == null : this.getFamilyId().equals(other.getFamilyId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGenusId() == null) ? 0 : getGenusId().hashCode());
        result = prime * result + ((getGenusName() == null) ? 0 : getGenusName().hashCode());
        result = prime * result + ((getFamilyName() == null) ? 0 : getFamilyName().hashCode());
        result = prime * result + ((getFamilyId() == null) ? 0 : getFamilyId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", genusId=").append(genusId);
        sb.append(", genusName=").append(genusName);
        sb.append(", familyName=").append(familyName);
        sb.append(", familyId=").append(familyId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}