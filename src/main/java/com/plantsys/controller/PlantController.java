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
import com.plantsys.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("plant")
public class PlantController {


    @Autowired
    PlantService plantService;

    @Autowired
    GenusService genusService;
    @Autowired
    FamilyService familyService;
    @Autowired
    PlantDiseaseService plantDiseaseService;
    @Autowired
    PictureService pictureService;
    @Autowired
    MaintenanceTaskService maintenanceTaskService;
    @Autowired
    MonitorValueService monitorValueService;
    @Autowired
    PlantInfoService plantInfoService;


    //添加植物
    @RequestMapping("addPlant")
    public ResultObj addPlant(Plant plant){
        QueryWrapper<Plant> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("plant_id",plant.getPlantId());
        int count=this.plantService.count(queryWrapper);
        if(count>0){
            return new ResultObj(-1,"植物已存在");
        }
        System.out.println(plant.getPlantName());
        try{
            this.plantService.save(plant);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }



    //修改植物
    @RequestMapping("updatePlant")
    public ResultObj update(Plant plant){
        try{
            this.plantService.updateById(plant);
            return ResultObj.OPERATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.OPERATE_ERRO;
        }
    }
    //删除植物
    @RequestMapping("deletePlant")
    public ResultObj deletePlant(Integer plantId){

        try{
            System.out.println(plantId);
            Plant plant=this.plantService.getById(plantId);
            this.pictureService.deleteByPlantId(plantId);
            this.maintenanceTaskService.deleteByPlantId(plantId);
            this.monitorValueService.deleteByPlantId(plantId);

            this.plantService.removeById(plantId);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


    //查询植物
    @RequestMapping("plantList")
    public DataGridView plantList(QueryVo queryVo){
        System.out.println("awfdawdaw");
        String loginName = (String) WebUtils.getHttpSession().getAttribute("loginName");

        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getLimit());
        System.out.println("1");
        System.out.println(queryVo);
        QueryWrapper<PlantInfo> queryWrapper = new QueryWrapper<>();
        // 模糊查询或查询所有植物
        queryWrapper.like(null != queryVo.getPlantId(), "plant_id", queryVo.getPlantId());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getPlantName()), "plant_name", queryVo.getPlantName());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getFeature()), "feature", queryVo.getFeature());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getValue()), "value", queryVo.getValue());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getPoint()), "point", queryVo.getPoint());
        queryWrapper.like(StrUtil.isNotBlank(queryVo.getAlias()), "alias", queryVo.getAlias());
        List<PlantInfo> data =this.plantInfoService.list(queryWrapper);
        if (null != data) {
            data.stream().map(item -> {
//                if(null!=item.getGenusId() && null!=item.getDiseaseId()){
//                    Genus genus = genusService.getById(item.getGenusId());
//                    System.out.println("5");
//                    item.setGenusName(null != genus ? genus.getGenusName() : "");
//
//                    PlantDisease plantDisease=plantDiseaseService.getById(item.getDiseaseId());
//                    item.setDiseaseName(null != plantDisease ? plantDisease.getName(): "");
//
//                    Family family=familyService.getById(genus.getFamilyId());
//                    item.setFamilyName(null!=family? family.getFamilyName():"");
//                }else {
//                    item.setGenusName("");
//                    item.setDiseaseName("");
//                    item.setFamilyName("");
//                }

                return item;
            }).collect(Collectors.toList());
        }
        return new DataGridView(page.getTotal(),data);

    }

    @RequestMapping("showPictures")
    public List<Picture> viewPictures(@RequestParam("plantId") int plantId, Model model){
        List<Picture> pictures = this.pictureService.selectAllByPlantId(plantId);
        model.addAttribute("pictures", pictures);
        return pictures;
    }
    @RequestMapping("addPlantPicture")
    public ResultObj addPlantPicture(Picture picture){
        QueryWrapper<Picture> wrapper=new QueryWrapper<>();
        wrapper.eq("picture_id",picture.getPictureId());
        int count = this.pictureService.count(wrapper);
        System.out.println("picture.getPlantId()"+picture.getPlantId());
        if(count>0){
            return new ResultObj(-1,"图片已存在");
        }
        try{
            this.pictureService.save(picture);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    @RequestMapping("deletePicture")
    public ResultObj deletePicture(Integer pictureId){
        try{
            System.out.println("pictureId:"+pictureId);
            this.pictureService.removeById(pictureId);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
