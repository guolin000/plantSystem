package com.plantsys.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.plantsys.Vo.DiseaseInfoVo;
import com.plantsys.Vo.DiseaseVo;
import com.plantsys.Vo.MedicamentInfoVo;
import com.plantsys.Vo.TreatmentInfoVo;
import com.plantsys.entity.*;
import com.plantsys.service.*;
import com.plantsys.util.DataGridView;
import com.plantsys.util.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("disease")
public class DiseaseController {
    @Autowired
    DiseaseService diseaseService;
    @Autowired
    DiseaseInfoService diseaseInfoService;

    @Autowired
    TreatmentService treatmentService;
    @Autowired
    TreatmentInfoService treatmentInfoService;

    @Autowired
    MedicamentService medicamentService;
    @Autowired
    MedicamentInfoService medicamentInfoService;

    @RequestMapping("diseaseInfoList")
    public DataGridView diseaseInfoList(DiseaseInfoVo diseaseInfoVo){
        Page<Object> page = PageHelper.startPage(diseaseInfoVo.getPage(), diseaseInfoVo.getLimit());
        QueryWrapper<DiseaseInfo> queryWrapper = new QueryWrapper<>();

        // 模糊查询或查询所有患病植物信息
        queryWrapper.like(null != diseaseInfoVo.getPlantId(), "plant_id", diseaseInfoVo.getPlantId());
        queryWrapper.like(StrUtil.isNotBlank(diseaseInfoVo.getName()), "name", diseaseInfoVo.getName());
        queryWrapper.like(StrUtil.isNotBlank(diseaseInfoVo.getPlantName()), "plant_name", diseaseInfoVo.getPlantName());
        queryWrapper.like(StrUtil.isNotBlank(diseaseInfoVo.getTreatMethod()), "treat_method", diseaseInfoVo.getTreatMethod());
        List<DiseaseInfo> data = this.diseaseInfoService.list(queryWrapper);

        return new DataGridView(page.getTotal(),data);
    }

    @RequestMapping("diseaseList")
    public DataGridView diseaseInfoList(DiseaseVo diseaseVo){
        Page<Object> page = PageHelper.startPage(diseaseVo.getPage(), diseaseVo.getLimit());
        QueryWrapper<Disease> queryWrapper = new QueryWrapper<>();

        // 模糊查询或查询所有患病植物信息
        queryWrapper.like(null != diseaseVo.getDiseaseId(), "disease_id", diseaseVo.getDiseaseId());
        queryWrapper.like(StrUtil.isNotBlank(diseaseVo.getDiseaseName()), "disease_name", diseaseVo.getDiseaseName());
        queryWrapper.like(StrUtil.isNotBlank(diseaseVo.getTreatMethod()), "treat_method", diseaseVo.getTreatMethod());
        List<Disease> data = this.diseaseService.list(queryWrapper);

        return new DataGridView(page.getTotal(),data);
    }
    @RequestMapping("addDisease")
    public ResultObj addDisease(Disease disease){
        try{
            this.diseaseService.save(disease);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    @RequestMapping("updateDisease")
    public ResultObj updateDisease(Disease disease){
        try{
            this.diseaseService.updateById(disease);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    @RequestMapping("deleteDisease")
    public ResultObj deleteDisease(Integer diseaseId){
        try{
//            System.out.println("medicamentId:"+medicamentId);
            this.diseaseService.removeById(diseaseId);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }



    @RequestMapping("treatmentInfoList")
    public DataGridView treatmentInfoList(TreatmentInfoVo treatmentInfoVo){
        Page<Object> page = PageHelper.startPage(treatmentInfoVo.getPage(), treatmentInfoVo.getLimit());
        QueryWrapper<TreatmentInfo> queryWrapper = new QueryWrapper<>();

        queryWrapper.like(null != treatmentInfoVo.getTreatmentId(), "treatment_id", treatmentInfoVo.getTreatmentId());
        queryWrapper.like(null !=treatmentInfoVo.getPlantId(), "plant_id", treatmentInfoVo.getPlantId());
        queryWrapper.like(null !=treatmentInfoVo.getStatus(), "status", treatmentInfoVo.getStatus());
        queryWrapper.like(StrUtil.isNotBlank(treatmentInfoVo.getPlantName()), "plant_name", treatmentInfoVo.getPlantName());
        queryWrapper.like(StrUtil.isNotBlank(treatmentInfoVo.getDiseaseName()), "disease_name", treatmentInfoVo.getDiseaseName());
        queryWrapper.like(StrUtil.isNotBlank(treatmentInfoVo.getLoginName()), "login_name", treatmentInfoVo.getLoginName());
        List<TreatmentInfo> data = this.treatmentInfoService.list(queryWrapper);

        return new DataGridView(page.getTotal(),data);
    }
    @RequestMapping("addTreatment")
    public ResultObj addTreatment(Treatment treatment){
        try{
            treatment.setStatus(0);
            this.treatmentService.save(treatment);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    @RequestMapping("updateTreatment")
    public ResultObj updateTreatment(Treatment treatment){
        try{
            this.treatmentService.updateById(treatment);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    @RequestMapping("deleteTreatment")
    public ResultObj deleteTreatment(Integer treatmentId){
        try{
//            System.out.println("medicamentId:"+medicamentId);
            this.treatmentService.removeById(treatmentId);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    @RequestMapping("updateStatus")
    public ResultObj updateStatus(Treatment treatment){
        try{
            Treatment t=this.treatmentService.getById(treatment);
            if(0==treatment.getStatus()){
                t.setStatus(1);
            }
            this.treatmentService.updateById(treatment);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    @RequestMapping("medicamentInfoList")
    public DataGridView medicamentInfoList(MedicamentInfoVo medicamentInfoVo){
        Page<Object> page = PageHelper.startPage(medicamentInfoVo.getPage(), medicamentInfoVo.getLimit());
        QueryWrapper<MedicamentInfo> queryWrapper = new QueryWrapper<>();

        // 模糊查询或查询所有药剂信息
        queryWrapper.like(null != medicamentInfoVo.getMedicamentId(), "medicament_id", medicamentInfoVo.getMedicamentId());
        queryWrapper.like(StrUtil.isNotBlank(medicamentInfoVo.getMedicamentName()), "medicament_name", medicamentInfoVo.getMedicamentName());
        queryWrapper.like(StrUtil.isNotBlank(medicamentInfoVo.getDosage()), "dosage", medicamentInfoVo.getDosage());
        queryWrapper.like(StrUtil.isNotBlank(medicamentInfoVo.getDuration()), "duration", medicamentInfoVo.getDuration());
        queryWrapper.like(StrUtil.isNotBlank(medicamentInfoVo.getDiseaseName()), "disease_name", medicamentInfoVo.getDiseaseName());

        List<MedicamentInfo> data = this.medicamentInfoService.list(queryWrapper);

        return new DataGridView(page.getTotal(),data);
    }
    @RequestMapping("addOrUpdateMedicament")
    public ResultObj addMedicament(Medicament medicament){
        try{
            System.out.println("medicament.getMedicamentName():"+medicament.getMedicamentName());
            this.medicamentService.saveOrUpdate(medicament);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    @RequestMapping("deleteMedicament")
    public ResultObj deletePicture(Integer medicamentId){
        try{
//            System.out.println("medicamentId:"+medicamentId);
            this.treatmentService.deleteByMedicamentId(medicamentId);
            this.medicamentService.removeById(medicamentId);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
