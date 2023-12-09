package com.plantsys.service;

import com.plantsys.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【picture】的数据库操作Service
* @createDate 2023-12-09 11:43:28
*/
public interface PictureService extends IService<Picture> {
    int deleteByPlantId(Integer plantId);

    List<Picture> selectAllByPlantId(Integer plantId);
}
