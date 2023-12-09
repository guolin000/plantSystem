package com.plantsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.entity.Picture;
import com.plantsys.service.PictureService;
import com.plantsys.mapper.PictureMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Administrator
* @description 针对表【picture】的数据库操作Service实现
* @createDate 2023-12-09 11:43:28
*/
@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture>
    implements PictureService {
    @Override
    public int deleteByPlantId(Integer plantId) {
        return this.baseMapper.deleteByPlantId(plantId);
    }

    @Override
    public List<Picture> selectAllByPlantId(Integer plantId) {
        return this.baseMapper.selectAllByPlantId(plantId);
    }
}




