package com.plantsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.entity.MonitorValue;
import com.plantsys.service.MonitorValueService;
import com.plantsys.mapper.MonitorValueMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【monitor_value】的数据库操作Service实现
* @createDate 2023-12-09 14:47:08
*/
@Service
public class MonitorValueServiceImpl extends ServiceImpl<MonitorValueMapper, MonitorValue>
    implements MonitorValueService{
    @Override
    public int deleteByPlantId(Integer plantId) {
        return this.baseMapper.deleteByPlantId(plantId);
    }
}




