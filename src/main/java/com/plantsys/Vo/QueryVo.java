package com.plantsys.Vo;


import lombok.Data;

/**
 */
@Data
public class QueryVo {
    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;
    private String plantName;
    private Integer plantId;
    /**
     * 作者
     */
    private String feature;

    /**
     * 出版社
     */
    private String value;

    private String point;

    private String alias;

}
