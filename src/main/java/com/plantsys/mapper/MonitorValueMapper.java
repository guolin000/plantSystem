package com.plantsys.mapper;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.plantsys.entity.MonitorValue;
import org.apache.ibatis.annotations.Param;

/**
* @author Administrator
* @description 针对表【monitor_value】的数据库操作Mapper
* @createDate 2023-12-05 15:09:43
* @Entity com.plantsys.entity.MonitorValue
*/
public interface MonitorValueMapper extends BaseMapper<MonitorValue> {
    int deleteByPlantId(@Param("plantId") Integer plantId);

    int addSelective(MonitorValue monitorValue);

    List<MonitorValue> selectByRecordId(@Param("recordId") Integer recordId);

    int deleteSelective(MonitorValue monitorValue);
}




