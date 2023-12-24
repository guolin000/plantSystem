package com.plantsys.mapper;
import org.apache.ibatis.annotations.Param;

import com.plantsys.entity.Medicament;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Administrator
* @description 针对表【medicament】的数据库操作Mapper
* @createDate 2023-12-09 23:56:09
* @Entity com.plantsys.entity.Medicament
*/
public interface MedicamentMapper extends BaseMapper<Medicament> {
    int addSelective(Medicament medicament);

    int deleteByMedicamentIdAdd(@Param("medicamentId")Integer medicamentId);

    List<Medicament> selectByMedicamentId(@Param("medicamentId")Integer medicamentId);

    List<Medicament> selectByDiseaseId(@Param("diseaseId")Integer diseaseId);

    int updateSelective(Medicament medicament);
}




