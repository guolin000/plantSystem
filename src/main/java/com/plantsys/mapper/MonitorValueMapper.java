package com.plantsys.mapper;

import com.plantsys.entity.MonitorValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Administrator
* @description 针对表【monitor_value】的数据库操作Mapper
* @createDate 2023-12-09 14:47:08
* @Entity com.plantsys.entity.MonitorValue
*/
public interface MonitorValueMapper extends BaseMapper<MonitorValue> {
    int deleteByPlantId(@Param("plantId") Integer plantId);
}




