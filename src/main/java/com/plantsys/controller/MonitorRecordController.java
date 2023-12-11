package com.plantsys.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plantsys.Vo.MonitorVo;
import com.plantsys.entity.MonitorRecord;
import com.plantsys.entity.MonitorRecordInfo;
import com.plantsys.entity.MonitorValue;
import com.plantsys.service.MonitorRecordInfoService;
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
public class MonitorRecordController {


    @Autowired
    UserService userService;
    @Autowired
    MonitorRecordService monitorRecordService;
    @Autowired
    MonitorRecordInfoService monitorRecordInfoService;
    @Autowired
    MonitorValueService monitorValueService;


    //跳转到监测模块
    @RequestMapping("toMonitorManager")
    public ModelAndView monitorRecordList(){
        return new ModelAndView("monitor/monitorManager");
    }


    //添加监测记录
    @RequestMapping("addMonitorRecord")
    public ResultObj addMonitorRecord(MonitorRecord monitorRecord){
        try{
            monitorRecordService.addSelective(monitorRecord);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }


    //修改监测记录
    @RequestMapping("updateMonitorRecord")
    public ResultObj updateMonitorRecord(MonitorRecord monitorRecord){
        try{
            monitorRecordService.updateSelective(monitorRecord);
            return ResultObj.OPERATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.OPERATE_ERRO;
        }
    }


    //删除监测记录
    @RequestMapping("deleteMonitorRecord")
    @ResponseBody
    public ResultObj deleteMonitorRecord(Integer recordId){
        try{
            monitorValueService.deleteByPlantId(recordId); // 先删除监测记录指标值
            monitorRecordService.deleteByRecordId(recordId);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


    //查询监测记录
    @RequestMapping("monitorRecordList")
    public DataGridView monitorRecordList(MonitorVo monitorVo){
        System.out.println("awfdawdaw");
        Page<Object> page = PageHelper.startPage(monitorVo.getPage(), monitorVo.getLimit());
        System.out.println("1");
        System.out.println(monitorVo);
        QueryWrapper<MonitorRecordInfo> queryWrapper = new QueryWrapper<>();
        // 模糊查询或查询所有记录
        queryWrapper.like(null != monitorVo.getRecordId(), "record_id", monitorVo.getRecordId());
        queryWrapper.like(StrUtil.isNotBlank(monitorVo.getEquipmentName()), "equipment_name", monitorVo.getEquipmentName());
        queryWrapper.like(StrUtil.isNotBlank(monitorVo.getLoginName()), "login_name", monitorVo.getLoginName());
        queryWrapper.like(null != monitorVo.getMonitorTime(), "monitor_time", monitorVo.getMonitorTime());
        queryWrapper.like(null != monitorVo.getMonitorSite(), "monitor_site", monitorVo.getMonitorSite());
        List<MonitorRecordInfo> data =this.monitorRecordInfoService.list(queryWrapper);
        if (null != data) {
            data.stream().collect(Collectors.toList());
        }
        return new DataGridView(page.getTotal(),data);

    }

}
