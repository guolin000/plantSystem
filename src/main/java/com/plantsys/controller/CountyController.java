package com.plantsys.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plantsys.Vo.QueryVo;
import com.plantsys.entity.City;
import com.plantsys.entity.CityInfo;
import com.plantsys.entity.County;
import com.plantsys.entity.CountyInfo;
import com.plantsys.service.CityInfoService;
import com.plantsys.service.CityService;
import com.plantsys.service.CountyInfoService;
import com.plantsys.service.CountyService;
import com.plantsys.util.DataGridView;
import com.plantsys.util.ResultObj;
import com.plantsys.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("county")
public class CountyController {
    @Autowired
    CountyInfoService countyInfoService;
    @Autowired
    CountyService countyService;

    // 添加县
    @RequestMapping("addCounty")
    public ResultObj addCounty(County county) {
        QueryWrapper<County> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("county_id", county.getCountyId());
        int count = this.countyService.count(queryWrapper);
        if (count > 0) {
            return new ResultObj(-1, "县已存在");
        }
        try {
            countyService.save(county);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    // 更新县
    @RequestMapping("updateCounty")
    public ResultObj updateCounty(County county) {
        try {
            countyService.updateById(county);
            return ResultObj.OPERATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.OPERATE_ERRO;
        }
    }

    @RequestMapping("countyList")
    public DataGridView countyList(QueryVo queryVo) {
        System.out.println("awfdawdaw");
        String loginName = (String) WebUtils.getHttpSession().getAttribute("loginName");
        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getLimit());
        System.out.println("1");
        System.out.println(queryVo);
        QueryWrapper<CountyInfo> queryWrapper = new QueryWrapper<>();
        // 模糊查询或查询所有植物
        queryWrapper.like(null != queryVo.getCountyId(), "county_id", queryVo.getCountyId());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getCountyName()), "county_name", queryVo.getCountyName());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getCityName()), "city_name", queryVo.getCityName());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getProvinceName()), "province_name", queryVo.getProvinceName());
        List<CountyInfo> data = this.countyInfoService.list(queryWrapper);
        return new DataGridView(page.getTotal(), data);
    }

    // 删除县
    @RequestMapping("deleteCounty")
    public ResultObj deleteCounty(Integer countyId) {
        try {
            countyService.removeById(countyId);
            // 你可能需要处理级联删除或其他相关操作
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
