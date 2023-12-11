package com.plantsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName monitor_value_info
 */
@TableName(value ="monitor_value_info")
@Data
public class MonitorValueInfo implements Serializable {
    /**
     * 
     */
    private Integer recordId;

    /**
     * 
     */
    private Integer plantId;

    /**
     * 
     */
    private Integer indicatorId;

    /**
     * 
     */
    private Double indicatorValue;

    /**
     * 
     */
    private String plantName;

    /**
     * 
     */
    private String indicatorName;

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
        MonitorValueInfo other = (MonitorValueInfo) that;
        return (this.getRecordId() == null ? other.getRecordId() == null : this.getRecordId().equals(other.getRecordId()))
            && (this.getPlantId() == null ? other.getPlantId() == null : this.getPlantId().equals(other.getPlantId()))
            && (this.getIndicatorId() == null ? other.getIndicatorId() == null : this.getIndicatorId().equals(other.getIndicatorId()))
            && (this.getIndicatorValue() == null ? other.getIndicatorValue() == null : this.getIndicatorValue().equals(other.getIndicatorValue()))
            && (this.getPlantName() == null ? other.getPlantName() == null : this.getPlantName().equals(other.getPlantName()))
            && (this.getIndicatorName() == null ? other.getIndicatorName() == null : this.getIndicatorName().equals(other.getIndicatorName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRecordId() == null) ? 0 : getRecordId().hashCode());
        result = prime * result + ((getPlantId() == null) ? 0 : getPlantId().hashCode());
        result = prime * result + ((getIndicatorId() == null) ? 0 : getIndicatorId().hashCode());
        result = prime * result + ((getIndicatorValue() == null) ? 0 : getIndicatorValue().hashCode());
        result = prime * result + ((getPlantName() == null) ? 0 : getPlantName().hashCode());
        result = prime * result + ((getIndicatorName() == null) ? 0 : getIndicatorName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", recordId=").append(recordId);
        sb.append(", plantId=").append(plantId);
        sb.append(", indicatorId=").append(indicatorId);
        sb.append(", indicatorValue=").append(indicatorValue);
        sb.append(", plantName=").append(plantName);
        sb.append(", indicatorName=").append(indicatorName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}