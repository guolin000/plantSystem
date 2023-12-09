package com.plantsys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.plantsys.entity.MonitorRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author JIALIANGLI
* @description 针对表【monitor_record】的数据库操作Service
* @createDate 2023-12-09 11:18:18
*/
public interface MonitorRecordService extends IService<MonitorRecord> {

    int addSelective(MonitorRecord monitorRecord);

    int deleteByRecordId(@Param("recordId") Integer recordId);

    List<MonitorRecord> selectByMap(Map<String, Object> map);

    int updateSelective(MonitorRecord monitorRecord);
}
