package com.xxx.mapper;

import com.xxx.base.BaseMapper;
import com.xxx.bean.RoleUser;

import java.util.List;

public interface RoleUserMapper extends BaseMapper<RoleUser,Integer> {
    /**
     * 根据对应用户id查找角色
     * @param userId
     * @return
     */
    Integer[] findRolesByUserId(Integer userId);

    /**
     * 删除对应用户id的角色
     * @param userId
     * @return
     */
    Integer deleteByUserid(Integer userId);

    /**
     * 根据用户添加角色
     * @param list
     * @return
     */
    int insertMsg(List<RoleUser> list);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteByManyUserid(Integer[] ids);
}