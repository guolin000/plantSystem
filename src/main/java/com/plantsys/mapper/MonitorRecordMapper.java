package com.plantsys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.plantsys.entity.MonitorRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author JIALIANGLI
* @description 针对表【monitor_record】的数据库操作Mapper
* @createDate 2023-12-09 11:18:18
* @Entity com.plantsys.entity.MonitorRecord
*/
public interface MonitorRecordMapper extends BaseMapper<MonitorRecord> {

    int addSelective(MonitorRecord monitorRecord);

    int deleteByRecordId(@Param("recordId") Integer recordId);

    List<MonitorRecord> selectByMap(Map<String, Object> map);

    int updateSelective(MonitorRecord monitorRecord);

}




