package com.plantsys.Vo;

import com.plantsys.entity.MedicamentInfo;
import lombok.Data;

@Data
public class MedicamentInfoVo extends MedicamentInfo {
    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    private String code;
}
