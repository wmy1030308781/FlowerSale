package com.xxx.controller;

import com.xxx.base.BaseController;
import com.xxx.dto.MenuDto;
import com.xxx.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("menu")
public class MenuController extends BaseController {
    @Resource
    private MenuService ms;

    /**
     * 打开授权页面
     * @param roleId
     * @param model
     * @return
     */
    @RequestMapping("grantPage")
    public String toGrantPage(Integer roleId, Model model){
        if(roleId!=null){
            System.out.println(roleId);
            model.addAttribute("roleId",roleId);
        }
        return "role/grant";
    }


    @RequestMapping("getTree")
    @ResponseBody
    public List<MenuDto> getTree(Integer roleId){
        return ms.getTree(roleId);
    }
}
