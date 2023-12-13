package com.plantsys.mapper;

import com.plantsys.entity.RegionInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 18035
* @description 针对表【region_info】的数据库操作Mapper
* @createDate 2023-12-13 10:48:30
* @Entity com.plantsys.entity.RegionInfo
*/
public interface RegionInfoMapper extends BaseMapper<RegionInfo> {

    List<RegionInfo> selectAllBySpeciesId(Integer speciesId);
}




