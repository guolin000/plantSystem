package com.plantsys.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plantsys.Vo.QueryVo;
import com.plantsys.entity.*;
import com.plantsys.service.CityService;
import com.plantsys.service.CountyService;
import com.plantsys.service.ProvinceService;
import com.plantsys.util.DataGridView;
import com.plantsys.util.ResultObj;
import com.plantsys.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("province")
public class ProvinceController {
    @Autowired
    ProvinceService provinceService;
    @Autowired
    CityService cityService;
    @Autowired
    CountyService countyService;
    // 添加省
    @RequestMapping("addProvince")
    public ResultObj addProvince(Province province) {
        QueryWrapper<Province> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("province_id",province.getProvinceId());
        int count=this.provinceService.count(queryWrapper);
        if(count>0){
            return new ResultObj(-1,"省份已存在");
        }
        try {
            provinceService.save(province);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    // 更新省
    @RequestMapping("updateProvince")
    public ResultObj updateProvince(Province province) {
        try {
            provinceService.updateById(province);
            return ResultObj.OPERATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.OPERATE_ERRO;
        }
    }
    @RequestMapping("provinceList")
    public DataGridView provinceList(QueryVo queryVo) {
        System.out.println("awfdawdaw");
        String loginName = (String) WebUtils.getHttpSession().getAttribute("loginName");
        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getLimit());
        System.out.println("1");
        System.out.println(queryVo);
        QueryWrapper<Province> queryWrapper = new QueryWrapper<>();
        // 模糊查询或查询所有植物
        queryWrapper.like(null != queryVo.getProvinceId(), "province_id", queryVo.getProvinceId());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getProvinceName()), "province_name", queryVo.getProvinceName());
        List<Province> data =this.provinceService.list(queryWrapper);
        return new DataGridView(page.getTotal(),data);
    }
    // 删除省
    @RequestMapping("deleteProvince")
    public ResultObj deleteProvince(Integer provinceId) {
        try {
            //查询所有的市
            QueryWrapper<City>queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("province_id",provinceId);
            List<City>cities=this.cityService.list(queryWrapper);
            for(City city:cities)
            {
                QueryWrapper<County> countyWrapper = new QueryWrapper<>();
                countyWrapper.eq("city_id", city.getCityId());
                List<County> counties = countyService.list(countyWrapper);
                // 遍历删除县
                for (County county : counties) {
                    countyService.removeById(county.getCountyId());
                }
                // 删除市
                cityService.removeById(city.getCityId());
            }
            //删除省
            provinceService.removeById(provinceId);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
