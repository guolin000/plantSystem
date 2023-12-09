package com.plantsys.Vo;

import com.plantsys.entity.Menu;
import lombok.Data;

/**

 */
@Data
public class MenuVo extends Menu {

    private Integer page;
    private Integer limit;

}
