package com.plantsys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.plantsys.entity.MonitorRecordInfo;
import com.plantsys.entity.MonitorValue;
import com.plantsys.entity.MonitorValueInfo;
import com.plantsys.service.MonitorRecordService;
import com.plantsys.service.MonitorValueInfoService;
import com.plantsys.service.MonitorValueService;
import com.plantsys.service.UserService;
import com.plantsys.util.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MonitorValueController {


    @Autowired
    UserService userService;
    @Autowired
    MonitorValueService monitorValueService;
    @Autowired
    MonitorValueInfoService monitorValueInfoService;


    //查看监测指标值
    @RequestMapping("checkMonitorValue")
    @ResponseBody
    public List<MonitorValueInfo> checkMonitorValue(Integer recordId){
        QueryWrapper<MonitorValueInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("record_id",recordId);
        return monitorValueInfoService.list(queryWrapper);
    }


    //删除监测指标值
    @RequestMapping("addMonitorValue")
    @ResponseBody
    public ResultObj addMonitorValue(MonitorValue monitorValue){
        try{
            System.out.println("``````````````````````````````````");
            System.out.println(monitorValue);
            monitorValueService.addSelective(monitorValue);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }


    //删除监测指标值
    @RequestMapping("deleteMonitorValue")
    @ResponseBody
    public ResultObj deleteMonitorValue(MonitorValue monitorValue){
        try{
            monitorValueService.deleteSelective(monitorValue);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
