package com.plantsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plantsys.entity.Medicament;
import com.plantsys.service.MedicamentService;
import com.plantsys.mapper.MedicamentMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【medicament】的数据库操作Service实现
* @createDate 2023-12-09 23:56:09
*/
@Service
public class MedicamentServiceImpl extends ServiceImpl<MedicamentMapper, Medicament>
    implements MedicamentService{
}




