package com.plantsys.mapper;

import com.plantsys.entity.MaintenanceTask;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Administrator
* @description 针对表【maintenance_task】的数据库操作Mapper
* @createDate 2023-12-09 14:47:17
* @Entity com.plantsys.entity.MaintenanceTask
*/
public interface MaintenanceTaskMapper extends BaseMapper<MaintenanceTask> {
    int deleteByPlantId(@Param("plantId") Integer plantId);
}




