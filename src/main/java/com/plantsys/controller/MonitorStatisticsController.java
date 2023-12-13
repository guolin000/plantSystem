package com.plantsys.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plantsys.Vo.MonitorStatisticsVo;
import com.plantsys.entity.IndicatorValueInfo;
import com.plantsys.entity.Statistics;
import com.plantsys.service.IndicatorValueInfoService;
import com.plantsys.service.StatisticsService;
import com.plantsys.util.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MonitorStatisticsController {


    @Autowired
    StatisticsService statisticsService;
    @Autowired
    IndicatorValueInfoService indicatorValueInfoService;


    //跳转到监测统计
    @RequestMapping("toMonitorStatisticsManager")
    public ModelAndView toMonitorStatisticsManager() {
        return new ModelAndView("monitor/monitorStatisticsManager");
    }


    //统计/按日
    @RequestMapping("selectByDay")
    @ResponseBody
    public List<Statistics> selectByDay(String indicatorId, String plantId){
        return statisticsService.selectByDay(Integer.parseInt(indicatorId),Integer.parseInt(plantId));
    }


    //统计/按月
    @RequestMapping("selectByMonth")
    @ResponseBody
    public List<Statistics> selectByMonth(String indicatorId, String plantId){
        return statisticsService.selectByMonth(Integer.parseInt(indicatorId),Integer.parseInt(plantId));
    }


    //统计/按年
    @RequestMapping("selectByYear")
    @ResponseBody
    public List<Statistics> selectByYear(String indicatorId, String plantId){
        return statisticsService.selectByYear(Integer.parseInt(indicatorId),Integer.parseInt(plantId));
    }


    //查询监测统计
    @RequestMapping("monitorStatisticsList")
    public DataGridView monitorStatisticsList(MonitorStatisticsVo monitorStatisticsVo){
        System.out.println("awfdawdaw");
        Page<Object> page = PageHelper.startPage(monitorStatisticsVo.getPage(), monitorStatisticsVo.getLimit());
        System.out.println("1");
        System.out.println(monitorStatisticsVo);
        QueryWrapper<IndicatorValueInfo> queryWrapper = new QueryWrapper<>();
        // 模糊查询或查询所有记录
        queryWrapper.like(StrUtil.isNotBlank(monitorStatisticsVo.getIndicatorName()), "indicator_name", monitorStatisticsVo.getIndicatorName());
        queryWrapper.like(StrUtil.isNotBlank(monitorStatisticsVo.getPlantName()), "plant_name", monitorStatisticsVo.getPlantName());
        List<IndicatorValueInfo> data =this.indicatorValueInfoService.list(queryWrapper);
        System.out.println(data);
        if (null != data) {
            data.stream().collect(Collectors.toList());
        }
        return new DataGridView(page.getTotal(),data);

    }


}
