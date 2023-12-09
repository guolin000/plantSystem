package com.plantsys.Vo;

import com.plantsys.entity.User;
import lombok.Data;

/**
 */
@Data
public class UserVo extends User {
    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    private String code;

    /**
     * 接受多个角色的id
     */
    private Integer [] ids;

}
