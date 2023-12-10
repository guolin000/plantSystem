package com.plantsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.entity.MaintenanceTask;
import com.plantsys.mapper.MaintenanceTaskMapper;
import com.plantsys.service.MaintenanceTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author JIALIANGLI
* @description 针对表【maintenance_task】的数据库操作Service实现
* @createDate 2023-12-05 12:59:16
*/
@Service
public class MaintenanceTaskServiceImpl extends ServiceImpl<MaintenanceTaskMapper, MaintenanceTask>
    implements MaintenanceTaskService{

    @Autowired
    MaintenanceTaskMapper mapper;

    @Override
    public int addSelective(MaintenanceTask maintenanceTask) {
        return mapper.addSelective(maintenanceTask);
    }

    @Override
    public int deleteByTaskId(Integer taskId) {
        return mapper.deleteByTaskId(taskId);
    }

    @Override
    public int updateSelective(MaintenanceTask maintenanceTask) {
        return mapper.updateSelective(maintenanceTask);
    }

    @Override
    public List<MaintenanceTask> selectByMap(Map<String, Object> map) {
        return mapper.selectByMap(map);
    }

    @Override
    public int deleteByPlantId(Integer plantId) {
        return mapper.deleteByPlantId(plantId);
    }
}




