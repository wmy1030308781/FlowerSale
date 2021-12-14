package com.xxx.mapper;

import com.xxx.base.BaseMapper;
import com.xxx.bean.RoleMenu;

import java.util.List;

public interface RoleMenuMapper extends BaseMapper<RoleMenu,Integer> {

    /**
     * 角色授权
     * @param rList
     * @return
     */
    public int insertMenuOfRole(List<RoleMenu> rList);

    /**
     *根据角色id查询应有的权限
     * @param roleId
     * @return
     */
    public Integer[] getMenuByRoleId(Integer roleId);

    /**
     * 根据用户id查找权限
     * @param userId
     * @return
     */
    public List<Integer> getMenuByManyRoleId(Integer userId);

    /**
     * 根据角色id删除权限
     * @param roleId
     * @return
     */
    public Integer deleteMenuByRoleId(Integer roleId);
}