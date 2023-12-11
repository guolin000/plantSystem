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
public class MonitorVo {
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
}
