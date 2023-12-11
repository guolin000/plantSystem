package com.plantsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @TableName monitor_record_info
 */
@TableName(value ="monitor_record_info")
@Data
public class MonitorRecordInfo implements Serializable {
    /**
     *
     */
    private Integer recordId;

    /**
     *
     */
    private Integer equipmentId;

    /**
     *
     */
    private Integer userId;

    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date monitorTime;

    /**
     *
     */
    private String monitorSite;

    /**
     *
     */
    private String loginName;

    /**
     *
     */
    private String equipmentName;

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
        MonitorRecordInfo other = (MonitorRecordInfo) that;
        return (this.getRecordId() == null ? other.getRecordId() == null : this.getRecordId().equals(other.getRecordId()))
            && (this.getEquipmentId() == null ? other.getEquipmentId() == null : this.getEquipmentId().equals(other.getEquipmentId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getMonitorTime() == null ? other.getMonitorTime() == null : this.getMonitorTime().equals(other.getMonitorTime()))
            && (this.getMonitorSite() == null ? other.getMonitorSite() == null : this.getMonitorSite().equals(other.getMonitorSite()))
            && (this.getLoginName() == null ? other.getLoginName() == null : this.getLoginName().equals(other.getLoginName()))
            && (this.getEquipmentName() == null ? other.getEquipmentName() == null : this.getEquipmentName().equals(other.getEquipmentName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRecordId() == null) ? 0 : getRecordId().hashCode());
        result = prime * result + ((getEquipmentId() == null) ? 0 : getEquipmentId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getMonitorTime() == null) ? 0 : getMonitorTime().hashCode());
        result = prime * result + ((getMonitorSite() == null) ? 0 : getMonitorSite().hashCode());
        result = prime * result + ((getLoginName() == null) ? 0 : getLoginName().hashCode());
        result = prime * result + ((getEquipmentName() == null) ? 0 : getEquipmentName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", recordId=").append(recordId);
        sb.append(", equipmentId=").append(equipmentId);
        sb.append(", userId=").append(userId);
        sb.append(", monitorTime=").append(monitorTime);
        sb.append(", monitorSite=").append(monitorSite);
        sb.append(", loginName=").append(loginName);
        sb.append(", equipmentName=").append(equipmentName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
