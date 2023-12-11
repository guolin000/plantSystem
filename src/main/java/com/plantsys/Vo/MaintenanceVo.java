package com.plantsys.Vo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 */
@Data
public class MaintenanceVo {
    /**
     * 分页参数
     */
    private Integer page;

    private Integer limit;

    /**
     * 查询参数
     */

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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationTime;

    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    /**
     *
     */
    private Integer plantId;

    /**
     *
     */
    private Integer taskStatus;

    /**
     *
     */
    private String plantName;

    /**
     *
     */
    private String maintainerName;

    /**
     *
     */
    private String creatorName;
}
