package com.plantsys.mapper;

import com.plantsys.entity.Treatment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Administrator
* @description 针对表【treatment】的数据库操作Mapper
* @createDate 2023-12-10 01:26:57
* @Entity com.plantsys.entity.Treatment
*/
public interface TreatmentMapper extends BaseMapper<Treatment> {
    int deleteByMedicamentId(@Param("medicamentId") Integer medicamentId);
}




