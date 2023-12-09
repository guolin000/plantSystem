package com.plantsys.service;

import com.plantsys.entity.MonitorValue;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【monitor_value】的数据库操作Service
* @createDate 2023-12-09 14:47:08
*/
public interface MonitorValueService extends IService<MonitorValue> {
    int deleteByPlantId(Integer plantId);
}
