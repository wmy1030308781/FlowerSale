package com.xxx.mapper;

import com.xxx.base.BaseMapper;
import com.xxx.bean.Role;
import com.xxx.query.RoleQuery;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends BaseMapper<Role,Integer> {

    /**
     * 查询所有角色信息
     * @param query
     * @return
     */
    public List<Role> getRoleList(RoleQuery query);

    /**
     * 根据角色名查询
     * @param roleName
     * @return
     */
    public Role getRoleByName(String roleName);

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    public int deleteRoleById(Integer roleId);


    /**
     * 角色下拉框数据
     * @param userId
     * @return
     */
    @MapKey("")
    public List<Map<String,Object>> getSelectList(Integer userId);


}