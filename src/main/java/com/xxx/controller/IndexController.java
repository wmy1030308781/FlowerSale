package com.xxx.controller;

import com.xxx.base.BaseController;
import com.xxx.bean.User;
import com.xxx.service.UserService;
import com.xxx.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController extends BaseController {
    @Resource
    private UserService userService;
    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("welcome")
    public String wle(){
        return "welcome";
    }

    @RequestMapping("main")
    public String main(HttpServletRequest req){
        //查询用户信息
        int userid = LoginUserUtil.releaseUserIdFromCookie(req);

        //查询用户权限
       List<Integer> menuOfUser = userService.getMenuOfUser(userid);


        User user = userService.selectByPrimaryKey(userid);
        //存储
        req.getSession().setAttribute("permissions",menuOfUser);
        System.out.println(menuOfUser.toString());
        req.setAttribute("user",user);
        //System.out.println(user);
        return "main";
    }

}
