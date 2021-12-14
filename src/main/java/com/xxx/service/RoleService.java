package com.xxx.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.base.BaseService;

import com.xxx.bean.Role;
import com.xxx.bean.RoleMenu;
import com.xxx.mapper.RoleMapper;
import com.xxx.mapper.RoleMenuMapper;
import com.xxx.query.RoleQuery;
import com.xxx.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class RoleService extends BaseService<Role,Integer> {
    @Resource
    private RoleMapper rm;

    @Resource
    private RoleMenuMapper rmm;

    /**
     * 获取角色表信息
     * @param query
     * @return
     */
    public Map<String,Object> getList(RoleQuery query){
        Map<String,Object> map=new HashMap<String,Object>();
        List<Role> roleList = rm.getRoleList(query);
        PageHelper.startPage(query.getPage(),query.getLimit());
        PageInfo<Role> plist=new PageInfo<>(roleList);
        map.put("code",0);
        map.put("msg","success");
        map.put("count",plist.getTotal());
        map.put("data",plist.getList());
        return map;
    }

    /**
     * 添加角色
     * @param role
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRole(Role role){
        checkParam(role);
        role.setAvailable(1);
        role.setCreattime(new Date());
        role.setUpdatetime(new Date());
        AssertUtil.isTrue( rm.insertSelective(role)<1,"角色添加失败!");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateRole(Role role){
        checkParam(role);
        AssertUtil.isTrue(rm.updateByPrimaryKeySelective(role)<1,"更新失败!");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRole(Integer roleId){
        AssertUtil.isTrue(rm.deleteRoleById(roleId)<1,"删除失败!");
    }

    /**
     * 参数校验
     * @param role
     */
    private void checkParam(Role role){
        AssertUtil.isTrue(StringUtils.isBlank(role.getRolename()),"请输入角色名称!");
        Role temp = rm.getRoleByName(role.getRolename());
        AssertUtil.isTrue(temp!=null&&!(temp.getRoleid().equals(role.getRoleid())),"角色名重复!");
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoledesc()),"请输入角色描述!");
    }

    /**
     * 查询角色下拉框数据,包括回显
     * @param userId
     * @return
     */
    public List<Map<String,Object>> getSelectList(Integer userId){
        return rm.getSelectList(userId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void doGrant(Integer roleId,Integer[] mids){
        AssertUtil.isTrue(mids==null||mids.length==0,"没有选取角色权限!");
        Integer[] menuId = rmm.getMenuByRoleId(roleId);
        if(menuId!=null&&menuId.length!=0){
            //删除角色原有的权限
            AssertUtil.isTrue(rmm.deleteMenuByRoleId(roleId)!=menuId.length,"删除角色原有id失败!");
        }

        //添加角色权限
        List<RoleMenu> menus=new ArrayList<>();
        for (Integer m:mids) {
            RoleMenu data=new RoleMenu();
            data.setMid(m);
            data.setRid(roleId);
            menus.add(data);
        }
        AssertUtil.isTrue(rmm.insertMenuOfRole(menus)<menus.size(),"角色授权失败!");

    }
}
