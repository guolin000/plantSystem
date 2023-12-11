package com.plantsys.Vo;

import com.plantsys.entity.PlantInfo;
import lombok.Data;

@Data
public class PlantInfoVo extends PlantInfo {
    /**
     * 分页参数
     */
    private Integer page;

    private Integer limit;
    private String code;
}
