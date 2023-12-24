package com.plantsys.mapper;
import org.apache.ibatis.annotations.Param;

import com.plantsys.entity.Plant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Administrator
* @description 针对表【plant】的数据库操作Mapper
* @createDate 2023-12-09 11:42:56
* @Entity com.plantsys.entity.Plant
*/
public interface PlantMapper extends BaseMapper<Plant> {

    int addSelective(Plant plant);

    int delByPlantId(@Param("plantId") Integer plantId);

    int updateSelective(Plant plant);

    List<Plant> selectByPlantId(@Param("plantId") Integer plantId);

    List<Plant> selectByPlantName(@Param("plantName") String plantName);
}




