package com.xxx.service;

import com.xxx.base.BaseService;
import com.xxx.bean.Menu;
import com.xxx.dto.MenuDto;
import com.xxx.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuService extends BaseService<Menu,Integer> {
    @Resource
    private MenuMapper mm;

    /**
     * 权限树数据(回显)
     * @return
     */
    public List<MenuDto> getTree(Integer roleId){
        System.out.println(roleId+"<<<<权限树数据");
        List<Integer> menuOfRole = mm.getMenuOfRole(roleId);
        List<MenuDto> mlist=mm.getAllMenu();
        //处理已被分配的菜单,回显
        if(menuOfRole!=null&&menuOfRole.size()!=0){
            for (MenuDto m:mlist) {
                if(menuOfRole.contains(m.getId())){
                    m.setChecked(true);
                }
            }
        }

        return mlist;
    }
}
