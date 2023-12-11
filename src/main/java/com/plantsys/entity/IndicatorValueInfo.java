package com.plantsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName indicator_value_info
 */
@TableName(value ="indicator_value_info")
@Data
public class IndicatorValueInfo implements Serializable {
    /**
     * 
     */
    @TableField(value = "indicator_id")
    private Integer indicatorId;

    /**
     * 
     */
    @TableField(value = "indicator_name")
    private String indicatorName;

    /**
     * 
     */
    @TableField(value = "average_value")
    private Double averageValue;

    /**
     * 
     */
    @TableField(value = "maximum_value")
    private Double maximumValue;

    /**
     * 
     */
    @TableField(value = "minimum_value")
    private Double minimumValue;

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
        IndicatorValueInfo other = (IndicatorValueInfo) that;
        return (this.getIndicatorId() == null ? other.getIndicatorId() == null : this.getIndicatorId().equals(other.getIndicatorId()))
            && (this.getIndicatorName() == null ? other.getIndicatorName() == null : this.getIndicatorName().equals(other.getIndicatorName()))
            && (this.getAverageValue() == null ? other.getAverageValue() == null : this.getAverageValue().equals(other.getAverageValue()))
            && (this.getMaximumValue() == null ? other.getMaximumValue() == null : this.getMaximumValue().equals(other.getMaximumValue()))
            && (this.getMinimumValue() == null ? other.getMinimumValue() == null : this.getMinimumValue().equals(other.getMinimumValue()))
            && (this.getPlantId() == null ? other.getPlantId() == null : this.getPlantId().equals(other.getPlantId()))
            && (this.getPlantName() == null ? other.getPlantName() == null : this.getPlantName().equals(other.getPlantName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIndicatorId() == null) ? 0 : getIndicatorId().hashCode());
        result = prime * result + ((getIndicatorName() == null) ? 0 : getIndicatorName().hashCode());
        result = prime * result + ((getAverageValue() == null) ? 0 : getAverageValue().hashCode());
        result = prime * result + ((getMaximumValue() == null) ? 0 : getMaximumValue().hashCode());
        result = prime * result + ((getMinimumValue() == null) ? 0 : getMinimumValue().hashCode());
        result = prime * result + ((getPlantId() == null) ? 0 : getPlantId().hashCode());
        result = prime * result + ((getPlantName() == null) ? 0 : getPlantName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", indicatorId=").append(indicatorId);
        sb.append(", indicatorName=").append(indicatorName);
        sb.append(", averageValue=").append(averageValue);
        sb.append(", maximumValue=").append(maximumValue);
        sb.append(", minimumValue=").append(minimumValue);
        sb.append(", plantId=").append(plantId);
        sb.append(", plantName=").append(plantName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}