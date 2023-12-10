package com.plantsys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plantsys.Vo.MonitorVo;
import com.plantsys.entity.MonitorRecord;
import com.plantsys.entity.MonitorValue;
import com.plantsys.service.MonitorRecordService;
import com.plantsys.service.MonitorValueService;
import com.plantsys.service.UserService;
import com.plantsys.util.DataGridView;
import com.plantsys.util.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MonitorValueController {


    @Autowired
    UserService userService;
    @Autowired
    MonitorRecordService monitorRecordService;
    @Autowired
    MonitorValueService monitorValueService;


    //查看监测指标值
    @RequestMapping("checkMonitorValue")
    @ResponseBody
    public List<MonitorValue> checkMonitorValue(Integer recordId){
        List<MonitorValue> monitorValueList = monitorValueService.selectByRecordId(recordId);
        return monitorValueList;
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
