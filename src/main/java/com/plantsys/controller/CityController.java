package com.plantsys.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plantsys.Vo.QueryVo;
import com.plantsys.entity.City;
import com.plantsys.entity.CityInfo;
import com.plantsys.entity.County;
import com.plantsys.entity.Province;
import com.plantsys.service.CityInfoService;
import com.plantsys.service.CityService;
import com.plantsys.service.CountyService;
import com.plantsys.util.DataGridView;
import com.plantsys.util.ResultObj;
import com.plantsys.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("city")
public class CityController {
    @Autowired
    CityInfoService cityInfoService;
    @Autowired
    CityService cityService;
    @Autowired
    CountyService countyService;
    // 添加市
    @RequestMapping("addCity")
    public ResultObj addCity(City city) {
        QueryWrapper<City> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("city_id",city.getProvinceId());
        int count=this.cityService.count(queryWrapper);
        if(count>0){
            return new ResultObj(-1,"市已存在");
        }
        try {
            cityService.save(city);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    // 更新市
    @RequestMapping("updateCity")
    public ResultObj updateCity(City city) {
        try {
            cityService.updateById(city);
            return ResultObj.OPERATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.OPERATE_ERRO;
        }
    }
    @RequestMapping("cityList")
    public DataGridView cityList(QueryVo queryVo) {
        System.out.println("awfdawdaw");
        String loginName = (String) WebUtils.getHttpSession().getAttribute("loginName");
        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getLimit());
        System.out.println("1");
        System.out.println(queryVo);
        QueryWrapper<CityInfo> queryWrapper = new QueryWrapper<>();
        // 模糊查询或查询所有植物
        queryWrapper.like(null != queryVo.getCityId(), "city_id", queryVo.getCityId());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getCityName()), "city_name", queryVo.getCityName());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getProvinceName()), "province_name", queryVo.getProvinceName());
        List<CityInfo> data =this.cityInfoService.list(queryWrapper);
        return new DataGridView(page.getTotal(),data);
    }
    // 删除市
    @RequestMapping("deleteCity")
    public ResultObj deleteCity(Integer cityId) {
        try {
            // 查询该市下的所有县
            QueryWrapper<County> countyWrapper = new QueryWrapper<>();
            countyWrapper.eq("city_id", cityId);
            List<County> counties = countyService.list(countyWrapper);

            // 遍历删除县
            for (County county : counties) {
                countyService.removeById(county.getCountyId());
            }

            cityService.removeById(cityId);
            // 你可能需要处理级联删除或其他相关操作
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
