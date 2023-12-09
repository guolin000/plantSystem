package com.plantsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.entity.MonitorRecord;
import com.plantsys.mapper.MonitorRecordMapper;
import com.plantsys.service.MonitorRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author JIALIANGLI
* @description 针对表【monitor_record】的数据库操作Service实现
* @createDate 2023-12-09 11:18:18
*/
@Service
public class MonitorRecordServiceImpl extends ServiceImpl<MonitorRecordMapper, MonitorRecord>
    implements MonitorRecordService{
    @Autowired
    MonitorRecordMapper mapper;

    @Override
    public int addSelective(MonitorRecord monitorRecord) {
        return mapper.addSelective(monitorRecord);
    }

    @Override
    public int deleteByRecordId(Integer recordId) {
        return mapper.deleteByRecordId(recordId);
    }

    @Override
    public List<MonitorRecord> selectByMap(Map<String, Object> map) {
        return mapper.selectByMap(map);
    }

    @Override
    public int updateSelective(MonitorRecord monitorRecord) {
        return mapper.updateSelective(monitorRecord);
    }
}




