package com.plantsys.mapper;

import com.plantsys.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【menu】的数据库操作Mapper
* @createDate 2023-12-09 11:42:24
* @Entity com.plantsys.entity.Menu
*/
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 查询所有菜单
     */
    List<Menu> queryAllMenu(Menu menu);


    /**
     * 根据用户id查询菜单
     * @param available
     * @param userId
     * @return
     */
    List<Menu> queryMenuByUid(@Param("available") Integer available, @Param("uid") Integer userId);
}




