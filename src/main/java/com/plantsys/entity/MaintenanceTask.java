package com.plantsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName maintenance_task
 */
@TableName(value ="maintenance_task")
@Data
public class MaintenanceTask implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer taskId;

    /**
     * 
     */
    private String taskName;

    /**
     * 
     */
    private String taskDescription;

    /**
     * 
     */
    private Date maintenanceTime;

    /**
     * 
     */
    private String maintenanceSite;

    /**
     * 
     */
    private Integer uid;

    /**
     * 
     */
    private Integer creator;

    /**
     * 
     */
    private Date creationTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer plantId;

    /**
     * 
     */
    private Integer taskStatus;

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
        MaintenanceTask other = (MaintenanceTask) that;
        return (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getTaskName() == null ? other.getTaskName() == null : this.getTaskName().equals(other.getTaskName()))
            && (this.getTaskDescription() == null ? other.getTaskDescription() == null : this.getTaskDescription().equals(other.getTaskDescription()))
            && (this.getMaintenanceTime() == null ? other.getMaintenanceTime() == null : this.getMaintenanceTime().equals(other.getMaintenanceTime()))
            && (this.getMaintenanceSite() == null ? other.getMaintenanceSite() == null : this.getMaintenanceSite().equals(other.getMaintenanceSite()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreationTime() == null ? other.getCreationTime() == null : this.getCreationTime().equals(other.getCreationTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getPlantId() == null ? other.getPlantId() == null : this.getPlantId().equals(other.getPlantId()))
            && (this.getTaskStatus() == null ? other.getTaskStatus() == null : this.getTaskStatus().equals(other.getTaskStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getTaskName() == null) ? 0 : getTaskName().hashCode());
        result = prime * result + ((getTaskDescription() == null) ? 0 : getTaskDescription().hashCode());
        result = prime * result + ((getMaintenanceTime() == null) ? 0 : getMaintenanceTime().hashCode());
        result = prime * result + ((getMaintenanceSite() == null) ? 0 : getMaintenanceSite().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreationTime() == null) ? 0 : getCreationTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getPlantId() == null) ? 0 : getPlantId().hashCode());
        result = prime * result + ((getTaskStatus() == null) ? 0 : getTaskStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", taskId=").append(taskId);
        sb.append(", taskName=").append(taskName);
        sb.append(", taskDescription=").append(taskDescription);
        sb.append(", maintenanceTime=").append(maintenanceTime);
        sb.append(", maintenanceSite=").append(maintenanceSite);
        sb.append(", uid=").append(uid);
        sb.append(", creator=").append(creator);
        sb.append(", creationTime=").append(creationTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", plantId=").append(plantId);
        sb.append(", taskStatus=").append(taskStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}