package com.plantsys.Vo;

import lombok.Data;

@Data
public class DiseaseInfoVo {
    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;
    private String plantName;
    private Integer plantId;

    //病虫害名称
    private String name;

    private String treatMethod;
}
