package com.plantsys.service;

import com.plantsys.entity.RegionInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 18035
* @description 针对表【region_info】的数据库操作Service
* @createDate 2023-12-13 10:48:30
*/
public interface RegionInfoService extends IService<RegionInfo> {

    List<RegionInfo> selectAllBySpeciesId(Integer speciesId);

}
