package com.plantsys.Vo;

import com.plantsys.entity.TreatmentInfo;
import lombok.Data;

@Data
public class TreatmentInfoVo extends TreatmentInfo {
    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    private String code;
}
