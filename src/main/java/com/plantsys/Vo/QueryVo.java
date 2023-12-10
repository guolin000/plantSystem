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
    /**
     * 出版社
     */
    private Integer familyId;
    private String familyName;
    private Integer genusId;
    private String genusName;
    private Integer speciesId;
    private String speciesName;
    private String environment;
    private Integer provinceId;
    private String provinceName;
    private Integer cityId;
    private String cityName;
    private Integer countyId;
    private String countyName;
}
