package com.plantsys.service;

import com.plantsys.entity.MaintenanceTask;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【maintenance_task】的数据库操作Service
* @createDate 2023-12-09 14:47:17
*/
public interface MaintenanceTaskService extends IService<MaintenanceTask> {
    int deleteByPlantId(Integer plantId);
}
