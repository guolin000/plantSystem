package com.plantsys.Vo;

import com.plantsys.entity.Disease;
import lombok.Data;

@Data
public class DiseaseVo extends Disease {
    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

}
