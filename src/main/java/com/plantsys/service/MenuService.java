package com.plantsys.service;

import com.plantsys.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.plantsys.Vo.MenuVo;

import java.util.List;

/**
* @author Administrator
* @description 针对表【menu】的数据库操作Service
* @createDate 2023-12-09 11:42:24
*/
public interface MenuService extends IService<Menu> {
    /**
     * 查询所有的菜单返回List
     * @param menuVo
     * @return
     */
    List<Menu> queryAllMenuForList(MenuVo menuVo);

    /**
     *根据用户id查询用户可用菜单
     * */
    List<Menu> queryMenuByUserIdForList(MenuVo menuVo,Integer userId);
}
