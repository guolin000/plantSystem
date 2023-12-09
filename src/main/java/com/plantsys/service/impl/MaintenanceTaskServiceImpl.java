package com.plantsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.entity.MaintenanceTask;
import com.plantsys.service.MaintenanceTaskService;
import com.plantsys.mapper.MaintenanceTaskMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【maintenance_task】的数据库操作Service实现
* @createDate 2023-12-09 14:47:17
*/
@Service
public class MaintenanceTaskServiceImpl extends ServiceImpl<MaintenanceTaskMapper, MaintenanceTask>
    implements MaintenanceTaskService{
    @Override
    public int deleteByPlantId(Integer plantId) {
        return this.baseMapper.deleteByPlantId(plantId);
    }
}




