package com.plantsys.util;

import lombok.Data;

@Data
public class DataGridView {
    /**
     * 封装layui数据表格的数据对象
     */
    private Integer code=0;
    private String msg="";
    private Long count;
    private Object data;

    public DataGridView() {
    }

    public DataGridView(Object data) {
        super();
        this.data = data;
    }

    public DataGridView(Long count, Object data) {
        super();
        this.count = count;
        this.data = data;
    }
}
