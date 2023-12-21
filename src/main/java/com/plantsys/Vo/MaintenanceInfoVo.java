package com.plantsys.Vo;


import com.plantsys.entity.MaintenanceTaskInfo;
import lombok.Data;

/**
 */
@Data
public class MaintenanceInfoVo extends MaintenanceTaskInfo {
    /**
     * 分页参数
     */
    private Integer page;

    private Integer limit;

}
