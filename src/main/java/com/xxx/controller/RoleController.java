package com.xxx.controller;

import com.xxx.base.BaseController;
import com.xxx.base.ResultInfo;
import com.xxx.bean.Role;
import com.xxx.query.RoleQuery;
import com.xxx.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {
    @Resource
    private RoleService rs;
    /**
     * 展开角色页面
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "role/role";
    }


    /**
     * 跳转更新或删除页面
     * @param roleId
     * @param model
     * @return
     */
    @RequestMapping("addOrUpdate")
    public String toUpdateOrSavePage(Integer roleId, Model model){

        if(roleId!=null){
            Role role = rs.selectByPrimaryKey(roleId);
            model.addAttribute("role",role);
        }
        return "role/add_update";
    }


    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> getRoleList(RoleQuery query){
        return rs.getList(query);
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultInfo addRole(Role role){
        rs.addRole(role);
        return success("添加成功!");
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateRole(Role role){
        rs.updateRole(role);
        return success("更新成功!");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo updateRole(Integer roleId){
        rs.deleteRole(roleId);
        return success("删除成功!");
    }

    /**
     * 角色下拉框数据
     * @param userId
     * @return
     */
    @RequestMapping("getList")
    @ResponseBody
    public List<Map<String,Object>> getSelectList(Integer userId){
        return rs.getSelectList(userId);
    }

    @RequestMapping("grant")
    @ResponseBody
    public ResultInfo doGrant(Integer roleId,Integer[] mids){
        rs.doGrant(roleId,mids);
        return success("授权成功!");
    }
}
