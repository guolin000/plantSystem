package com.plantsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.entity.MonitorValue;
import com.plantsys.mapper.MonitorValueMapper;
import com.plantsys.service.MonitorValueService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Administrator
* @description 针对表【monitor_value】的数据库操作Service实现
* @createDate 2023-12-05 15:09:43
*/
@Service
public class MonitorValueServiceImpl extends ServiceImpl<MonitorValueMapper, MonitorValue>
    implements MonitorValueService{
    @Override
    public int deleteByPlantId(Integer plantId) {
        return this.baseMapper.deleteByPlantId(plantId);
    }

    public int addSelective(MonitorValue monitorValue) {
        return this.baseMapper.addSelective(monitorValue);
    }

    @Override
    public List<MonitorValue> selectByRecordId(Integer recordId) {
        return this.baseMapper.selectByRecordId(recordId);
    }

    @Override
    public int deleteSelective(MonitorValue monitorValue) {
        return this.baseMapper.deleteSelective(monitorValue);
    }
}




