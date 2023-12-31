package com.plantsys.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plantsys.Vo.MaintenanceInfoVo;
import com.plantsys.entity.MaintenanceTask;
import com.plantsys.entity.MaintenanceTaskInfo;
import com.plantsys.entity.User;
import com.plantsys.service.MaintenanceTaskInfoService;
import com.plantsys.service.MaintenanceTaskService;
import com.plantsys.service.UserService;
import com.plantsys.util.DataGridView;
import com.plantsys.util.ResultObj;
import com.plantsys.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class MaintenanceTaskController {


    @Autowired
    UserService userService;
    @Autowired
    MaintenanceTaskService maintenanceTaskService;
    @Autowired
    MaintenanceTaskInfoService maintenanceTaskInfoService;


    //跳转到养护列表
    @RequestMapping("toMaintenanceManager")
    public ModelAndView toMaintenanceManager(){
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("uid",user.getUserId());
        modelAndView.setViewName("maintenance/maintenanceManager");
        return modelAndView;
    }


    /*
     * 状态码:
     * 0:未完成
     * 1:已完成
     * */


    //完成任务
    @RequestMapping("completeMaintenanceTask")
    @ResponseBody
    public boolean completeMaintenanceTask(Integer taskId){
        boolean re = false;
        Map<String,Object> map = new HashMap<>();
        map.put("taskId", taskId);
        MaintenanceTask maintenanceTask = maintenanceTaskService.selectByMap(map).get(0);
        if(maintenanceTask.getTaskStatus()==0){
            maintenanceTask.setTaskStatus(1);
            maintenanceTask.setUpdateTime(DateUtil.now());
            maintenanceTaskService.updateSelective(maintenanceTask);
            re = true;
        }
        return re;
    }


    //添加养护任务
    @RequestMapping("addMaintenanceTask")
    public ResultObj addMaintenanceTask(MaintenanceTask maintenanceTask){
        try{
            System.out.println("");

            maintenanceTask.setCreationTime(DateUtil.now());
            maintenanceTask.setUpdateTime(DateUtil.now());
            maintenanceTaskService.addSelective(maintenanceTask);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }


    //修改养护任务
    @RequestMapping("updateMaintenanceTask")
    public ResultObj updateMaintenanceTask(MaintenanceTask maintenanceTask){
        try{
            maintenanceTask.setUpdateTime(DateUtil.now());
            maintenanceTaskService.updateSelective(maintenanceTask);
            return ResultObj.OPERATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.OPERATE_ERRO;
        }
    }


    //删除养护任务
    @RequestMapping("deleteMaintenanceTask")
    @ResponseBody
    public ResultObj deleteMaintenanceTask(Integer taskId){
        try{
            maintenanceTaskService.deleteByTaskId(taskId);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


    //查询养护任务
    @RequestMapping("maintenanceTaskList")
    public DataGridView maintenanceTaskList(MaintenanceInfoVo maintenanceInfoVo){
        Page<Object> page = PageHelper.startPage(maintenanceInfoVo.getPage(), maintenanceInfoVo.getLimit());
        System.out.println(maintenanceInfoVo);
        QueryWrapper<MaintenanceTaskInfo> queryWrapper = new QueryWrapper<>();
        // 模糊查询或查询所有记录
        queryWrapper.like(null != maintenanceInfoVo.getTaskId(), "task_id", maintenanceInfoVo.getTaskId());
        queryWrapper.like(StrUtil.isNotBlank(maintenanceInfoVo.getTaskName()), "task_name", maintenanceInfoVo.getTaskName());
        queryWrapper.like(StrUtil.isNotBlank(maintenanceInfoVo.getTaskDescription()), "task_description", maintenanceInfoVo.getTaskDescription());
        queryWrapper.like(null != maintenanceInfoVo.getMaintenanceTime(), "maintenance_time", maintenanceInfoVo.getMaintenanceTime());
        queryWrapper.like(StrUtil.isNotBlank(maintenanceInfoVo.getMaintenanceSite()), "maintenance_site", maintenanceInfoVo.getMaintenanceSite());
        queryWrapper.like(StrUtil.isNotBlank(maintenanceInfoVo.getMaintainerName()), "maintainer_name", maintenanceInfoVo.getMaintainerName());
        queryWrapper.like(StrUtil.isNotBlank(maintenanceInfoVo.getPlantName()), "plant_name", maintenanceInfoVo.getPlantName());
        queryWrapper.like(StrUtil.isNotBlank(maintenanceInfoVo.getCreatorName()), "creator_name", maintenanceInfoVo.getCreatorName());

        User user=(User) WebUtils.getHttpSession().getAttribute("user");
        if(user.getRid() == 3){
            queryWrapper.eq("uid",user.getUserId());
        }
        List<MaintenanceTaskInfo> data =this.maintenanceTaskInfoService.list(queryWrapper);
        if (null != data) {
            data.stream().collect(Collectors.toList());
        }
        return new DataGridView(page.getTotal(),data);

    }


}
