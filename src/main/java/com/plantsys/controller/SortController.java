package com.plantsys.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plantsys.Vo.QueryVo;
import com.plantsys.entity.*;
import com.plantsys.service.*;
import com.plantsys.util.DataGridView;
import com.plantsys.util.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sort")
public class SortController {
    @Autowired
    private FamilyService familyService;

    @Autowired
    private GenusService genusService;

    @Autowired
    private SpeciesService speciesService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private CityService cityService;

    @Autowired
    private CountyService countyService;
    // 添加科
    @RequestMapping("addFamily")
    public ResultObj addFamily(Family family) {
        QueryWrapper<Family> familyQueryWrapper=new QueryWrapper<>();
        familyQueryWrapper.eq("family_id",family.getFamilyId());
        int count=this.familyService.count(familyQueryWrapper);
        if(count>0){
            return new ResultObj(-1,"科目已存在");
        }
        System.out.println(family.getFamilyName());
        try {
            familyService.save(family);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    // 查询科列表
    @RequestMapping("familyList")
    public DataGridView familyList(QueryVo queryVo) {
        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getLimit());
        QueryWrapper<Family> queryWrapper = new QueryWrapper<>();
        // 模糊查询或查询所有科
        queryWrapper.like(null!=queryVo.getFamilyId(),"family_id",queryVo.getFamilyId());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getFamilyName()), "family_name", queryVo.getFamilyName());
        List<Family> data = familyService.list(queryWrapper);
        if(null!=data)
        {
            data.stream().collect(Collectors.toList());
        }
        return new DataGridView(page.getTotal(), data);
    }

    // 更新科
    @RequestMapping("updateFamily")
    public ResultObj updateFamily(Family family) {
        try {
            familyService.updateById(family);
            return ResultObj.OPERATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.OPERATE_ERRO;
        }
    }

    // 删除科
    @RequestMapping("deleteFamily")
    public ResultObj deleteFamily(Integer speciesId) {
        try {
            //得到属的ID
            Integer genusId=this.speciesService.getById(speciesId).getGenusId();
            //得到科的ID
            Integer familyId = this.genusService.getById(genusId).getFamilyId();
            //删除科
            familyService.removeById(familyId);
            //删除属
            genusService.removeById(genusId);
            //删除种
            speciesService.removeById(speciesId);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    // 添加属
    @RequestMapping("addGenus")
    public ResultObj addGenus(Genus genus) {
        QueryWrapper<Genus> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("genus_id",genus.getGenusId());
        int count=this.genusService.count(queryWrapper);
        if(count>0){
            return new ResultObj(-1,"属目已存在");
        }
        System.out.println(genus.getGenusName());
        try {
            genusService.save(genus);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    // 查询属列表
    @RequestMapping("genusList")
    public DataGridView genusList(QueryVo queryVo) {
        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getLimit());
        QueryWrapper<Genus> queryWrapper = new QueryWrapper<>();
        // 模糊查询或查询所有属
        queryWrapper.like(null!=queryVo.getGenusId(),"genus_id",queryVo.getGenusId());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getGenusName()), "genus_name", queryVo.getGenusName());
        queryWrapper.like(null!=queryVo.getFamilyId(), "family_id", queryVo.getFamilyId());
        List<Genus> data = genusService.list(queryWrapper);
        if(null!=data)
        {
            data.stream().collect(Collectors.toList());
        }
        return new DataGridView(page.getTotal(), data);
    }

    // 更新属
    @RequestMapping("updateGenus")
    public ResultObj updateGenus(Genus genus) {
        try {
            genusService.updateById(genus);
            return ResultObj.OPERATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.OPERATE_ERRO;
        }
    }

    // 删除属
    @RequestMapping("deleteGenus")
    public ResultObj deleteGenus(Integer speciesId) {
        try {
            //得到属的ID
            Integer genusId=this.speciesService.getById(speciesId).getGenusId();
            genusService.removeById(genusId);
            speciesService.removeById(speciesId);
            // 你可能需要处理级联删除或其他相关操作
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    // 类似的方法可以用于种、省、市和县的操作

    // 添加种
    @RequestMapping("addSpecies")
    public ResultObj addSpecies(Species species) {
        QueryWrapper<Species> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("species_id",species.getSpeciesId());
        int count=this.speciesService.count(queryWrapper);
        if(count>0){
            return new ResultObj(-1,"种目已存在");
        }
        System.out.println(species.getSpeciesName());
        try {
            speciesService.save(species);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    // 查询种列表
    @RequestMapping("speciesList")
    public DataGridView speciesList(QueryVo queryVo) {
        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getLimit());
        QueryWrapper<Species> queryWrapper = new QueryWrapper<>();
        // 模糊查询或查询所有种
        queryWrapper.like(null!=queryVo.getSpeciesId(),"species_id",queryVo.getSpeciesId());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getSpeciesName()), "species_name", queryVo.getSpeciesName());
        queryWrapper.like(null!=queryVo.getGenusId(),"genus_id",queryVo.getGenusId());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getAlias()), "alias", queryVo.getAlias());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getFeature()), "feature", queryVo.getFeature());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getValue()), "value", queryVo.getValue());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getPoint()), "point", queryVo.getPoint());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getEnvironment()), "environment", queryVo.getEnvironment());
        List<Species> data = speciesService.list(queryWrapper);
        if(null!=data)
        {
            data.stream().collect(Collectors.toList());
        }
        return new DataGridView(page.getTotal(), data);
    }

    // 更新种
    @RequestMapping("updateSpecies")
    public ResultObj updateSpecies(Species species) {
        try {
            speciesService.updateById(species);
            return ResultObj.OPERATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.OPERATE_ERRO;
        }
    }

    // 删除种
    @RequestMapping("deleteSpecies")
    public ResultObj deleteSpecies(Integer speciesId) {
        try {
            speciesService.removeById(speciesId);
            // 你可能需要处理级联删除或其他相关操作
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    // 添加省
    @RequestMapping("addProvince")
    public ResultObj addProvince(Province province) {
        QueryWrapper<Province> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("province_id",province.getProvinceId());
        int count=this.provinceService.count(queryWrapper);
        if(count>0){
            return new ResultObj(-1,"省份已存在");
        }
        System.out.println(province.getProvinceName());
        try {
            provinceService.save(province);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    // 查询省列表
    @RequestMapping("provinceList")
    public DataGridView provinceList(QueryVo queryVo) {
        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getLimit());
        QueryWrapper<Province> queryWrapper = new QueryWrapper<>();
        // 模糊查询或查询所有省
        queryWrapper.like(null!=queryVo.getProvinceId(),"province_id",queryVo.getProvinceId());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getProvinceName()), "province_name", queryVo.getProvinceName());
        List<Province> data = provinceService.list(queryWrapper);
        if(null!=data)
        {
            data.stream().collect(Collectors.toList());
        }
        return new DataGridView(page.getTotal(), data);
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

    // 删除省
    @RequestMapping("deleteProvince")
    public ResultObj deleteProvince(Integer countyId) {
        try {
            //得到市
            Integer cityId = this.countyService.getById(countyId).getCityId();
            //得到省
            Integer provinceId=this.cityService.getById(cityId).getProvinceId();
            //删除省
            provinceService.removeById(provinceId);
            //删除市
            cityService.removeById(cityId);
            //删除县
            countyService.removeById(countyId);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    // 添加市
    @RequestMapping("addCity")
    public ResultObj addCity(City city) {
        QueryWrapper<City> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("city_id",city.getProvinceId());
        int count=this.cityService.count(queryWrapper);
        if(count>0){
            return new ResultObj(-1,"市已存在");
        }
        System.out.println(city.getCityName());
        try {
            cityService.save(city);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    // 查询市列表
    @RequestMapping("cityList")
    public DataGridView cityList(QueryVo queryVo) {
        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getLimit());
        QueryWrapper<City> queryWrapper = new QueryWrapper<>();
        // 模糊查询或查询所有市
        queryWrapper.like(null!=queryVo.getCityId(),"city_id",queryVo.getCityId());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getCityName()), "city_name", queryVo.getCityName());
        queryWrapper.like(null!=queryVo.getProvinceId(),"province_id",queryVo.getProvinceId());
        List<City> data = cityService.list(queryWrapper);
        if(null!=data)
        {
            data.stream().collect(Collectors.toList());
        }
        return new DataGridView(page.getTotal(), data);
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

    // 删除市
    @RequestMapping("deleteCity")
    public ResultObj deleteCity(Integer countyId) {
        try {
            //得到市
            Integer cityId = this.countyService.getById(countyId).getCityId();
            cityService.removeById(cityId);
            countyService.removeById(countyId);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    // 添加县
    @RequestMapping("addCounty")
    public ResultObj addCounty(County county) {
        QueryWrapper<County> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("county_id",county.getCountyId());
        int count=this.countyService.count(queryWrapper);
        if(count>0){
            return new ResultObj(-1,"县已存在");
        }
        try {
            countyService.save(county);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    // 查询县列表
    @RequestMapping("countyList")
    public DataGridView countyList(QueryVo queryVo) {
        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getLimit());
        QueryWrapper<County> queryWrapper = new QueryWrapper<>();
        // 模糊查询或查询所有县
        queryWrapper.like(null!=queryVo.getCountyId(),"county_id",queryVo.getCountyId());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getCountyName()), "county_name", queryVo.getCountyName());
        queryWrapper.like(null!=queryVo.getCityId(),"city_id",queryVo.getCityId());
        List<County> data = countyService.list(queryWrapper);
        if(null!=data)
        {
            data.stream().collect(Collectors.toList());
        }
        return new DataGridView(page.getTotal(), data);
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

    // 删除县
    @RequestMapping("deleteCounty")
    public ResultObj deleteCounty(Integer countyId) {
        try {
            countyService.removeById(countyId);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
