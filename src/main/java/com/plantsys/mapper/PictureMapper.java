package com.plantsys.mapper;

import com.plantsys.entity.Picture;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【picture】的数据库操作Mapper
* @createDate 2023-12-09 11:43:28
* @Entity com.plantsys.entity.Picture
*/
public interface PictureMapper extends BaseMapper<Picture> {
    int deleteByPlantId(@Param("plantId") Integer plantId);

    List<Picture> selectAllByPlantId(@Param("plantId") Integer plantId);
}




