package com.plantsys.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plantsys.Vo.MaintenanceVo;
import com.plantsys.Vo.QueryVo;
import com.plantsys.entity.MaintenanceTask;
import com.plantsys.service.MaintenanceTaskService;
import com.plantsys.service.UserService;
import com.plantsys.util.DataGridView;
import com.plantsys.util.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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


    //跳转到养护模块
    @RequestMapping("toMaintenanceManager")
    public ModelAndView maintenanceTaskList(){
        return new ModelAndView("maintenance/list");
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
            maintenanceTaskService.updateSelective(maintenanceTask);
        }
        re = true;
        return re;
    }


    //添加养护任务
    @RequestMapping("addMaintenanceTask")
    public ResultObj addMaintenanceTask(MaintenanceTask maintenanceTask){
        try{
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
    public DataGridView maintenanceTaskList(MaintenanceVo maintenanceVo){
        System.out.println("awfdawdaw");
        Page<Object> page = PageHelper.startPage(maintenanceVo.getPage(), maintenanceVo.getLimit());
        System.out.println("1");
        System.out.println(maintenanceVo);
        QueryWrapper<MaintenanceTask> queryWrapper = new QueryWrapper<>();
        // 模糊查询或查询所有记录
        queryWrapper.like(null != maintenanceVo.getTaskId(), "task_id", maintenanceVo.getTaskId());
        queryWrapper.like(StrUtil.isNotBlank(maintenanceVo.getTaskName()), "task_name", maintenanceVo.getTaskName());
        queryWrapper.like(StrUtil.isNotBlank(maintenanceVo.getTaskDescription()), "task_description", maintenanceVo.getTaskDescription());
        queryWrapper.like(null != maintenanceVo.getMaintenanceTime(), "maintenance_time", maintenanceVo.getMaintenanceTime());
        queryWrapper.like(StrUtil.isNotBlank(maintenanceVo.getMaintenanceSite()), "maintenance_site", maintenanceVo.getMaintenanceSite());
        queryWrapper.like(null != maintenanceVo.getUid(), "uid", maintenanceVo.getUid());
        queryWrapper.like(null != maintenanceVo.getPlantId(), "plant_id", maintenanceVo.getPlantId());
        queryWrapper.like(null != maintenanceVo.getCreator(), "creator", maintenanceVo.getCreator());
        List<MaintenanceTask> data =this.maintenanceTaskService.list(queryWrapper);
        if (null != data) {
            data.stream().collect(Collectors.toList());
        }
        return new DataGridView(page.getTotal(),data);

    }


}
