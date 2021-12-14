package com.xxx.mapper;

import com.xxx.base.BaseMapper;
import com.xxx.bean.Menu;
import com.xxx.dto.MenuDto;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu,Integer> {
    /**
     * 获取菜单数据
     * @return
     */
    public List<MenuDto> getAllMenu();

    /**
     * 获取该角色的所有菜单
     * @param roleId
     * @return
     */
    public List<Integer> getMenuOfRole(Integer roleId);
}