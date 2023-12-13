package com.plantsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.entity.RegionInfo;
import com.plantsys.service.RegionInfoService;
import com.plantsys.mapper.RegionInfoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 18035
* @description 针对表【region_info】的数据库操作Service实现
* @createDate 2023-12-13 10:48:30
*/
@Service
public class RegionInfoServiceImpl extends ServiceImpl<RegionInfoMapper, RegionInfo>
    implements RegionInfoService{

    @Override
    public List<RegionInfo> selectAllBySpeciesId(Integer speciesId) {
        return this.baseMapper.selectAllBySpeciesId(speciesId);
    }
}




