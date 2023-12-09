package com.plantsys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.plantsys.entity.MaintenanceTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author JIALIANGLI
* @description 针对表【maintenance_task】的数据库操作Service
* @createDate 2023-12-05 12:59:16
*/
public interface MaintenanceTaskService extends IService<MaintenanceTask> {
    int addSelective(MaintenanceTask maintenanceTask);

    int deleteByTaskId(@Param("taskId") Integer taskId);

    int updateSelective(MaintenanceTask maintenanceTask);

    List<MaintenanceTask> selectByMap(@Param("map") Map<String, Object> map);

    int deleteByPlantId(@Param("plantId") Integer plantId);
}
