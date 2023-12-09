package com.plantsys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plantsys.Vo.QueryVo;
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
public class MonitorRecordController {


    @Autowired
    UserService userService;
    @Autowired
    MonitorRecordService monitorRecordService;
    @Autowired
    MonitorValueService monitorValueService;


    //跳转到监测模块
    @RequestMapping("toMonitorManager")
    public ModelAndView monitorRecordList(){
        return new ModelAndView("monitor/list");
    }


    //添加监测记录
    @RequestMapping("addMonitorRecord")
    public ResultObj addMonitorRecord(MonitorRecord monitorRecord, MonitorValue monitorValue){
        try{
            monitorRecordService.addSelective(monitorRecord);
            monitorValueService.addSelective(monitorValue); // 后添加指标值
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
            monitorValueService.deleteByPlantId(recordId); // 先删除指标值 B
            monitorRecordService.deleteByRecordId(recordId);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


    //查询监测记录
    @RequestMapping("monitorRecordList")
    public DataGridView monitorRecordList(QueryVo queryVo){
        System.out.println("awfdawdaw");
        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getLimit());
        System.out.println("1");
        System.out.println(queryVo);
        QueryWrapper<MonitorRecord> queryWrapper = new QueryWrapper<>();
        // 模糊查询或查询所有记录
//        queryWrapper.like(null != queryVo.getPlantId(), "plant_id", queryVo.getPlantId());
//        queryWrapper.like(StrUtil.isNotBlank(queryVo.getPlantName()), "plant_name", queryVo.getPlantName());
//        queryWrapper.like(StrUtil.isNotBlank(queryVo.getFeature()), "feature", queryVo.getFeature());
//        queryWrapper.like(StrUtil.isNotBlank(queryVo.getValue()), "value", queryVo.getValue());
//        queryWrapper.like(StrUtil.isNotBlank(queryVo.getPoint()), "point", queryVo.getPoint());
//        queryWrapper.like(StrUtil.isNotBlank(queryVo.getAlias()), "alias", queryVo.getAlias());
        List<MonitorRecord> data =this.monitorRecordService.list(queryWrapper);
        if (null != data) {
            data.stream().collect(Collectors.toList());
        }
        return new DataGridView(page.getTotal(),data);

    }

}
