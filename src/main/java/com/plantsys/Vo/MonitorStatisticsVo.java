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
public class MonitorStatisticsVo {
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
    private String indicatorName;

    /**
     *
     */
    private String plantName;
}
