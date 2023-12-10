package com.plantsys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.plantsys.entity.MonitorValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【monitor_value】的数据库操作Service
* @createDate 2023-12-05 15:09:43
*/
public interface MonitorValueService extends IService<MonitorValue> {
    int deleteByPlantId(Integer plantId);

    int addSelective(MonitorValue monitorValue);

    List<MonitorValue> selectByRecordId(@Param("recordId") Integer recordId);

    int deleteSelective(MonitorValue monitorValue);
}
