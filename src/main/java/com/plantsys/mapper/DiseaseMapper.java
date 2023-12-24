package com.plantsys.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.plantsys.entity.Disease;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Administrator
* @description 针对表【disease】的数据库操作Mapper
* @createDate 2023-12-10 00:18:34
* @Entity com.plantsys.entity.Disease
*/
public interface DiseaseMapper extends BaseMapper<Disease> {
    int addSelective(Disease disease);

    int delByDiseaseId(@Param("diseaseId") Integer diseaseId);

    int updateSelective(Disease disease);

    List<Disease> selectByDiseaseId(@Param("diseaseId") Integer diseaseId);

}




