package com.plantsys.Vo;

import com.plantsys.entity.FamilyPlantCount;
import lombok.Data;

@Data
public class FamilyPlantCountVo extends FamilyPlantCount {
    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    private String code;
}
