package com.xxx.controller;

import com.xxx.annotation.PermissionProxy;
import com.xxx.base.BaseController;
import com.xxx.base.ResultInfo;
import com.xxx.bean.User;
import com.xxx.model.UserModel;
import com.xxx.query.UserQuery;
import com.xxx.service.UserService;
import com.xxx.utils.AssertUtil;
import com.xxx.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    @Resource
    private UserService us;

    @RequestMapping("index")
    public String index(){
        return "user/user";
    }

    //登录
    @RequestMapping("login")
    @ResponseBody
    public ResultInfo saylogin(String loginName, String pwd) {
        ResultInfo re = new ResultInfo();
        UserModel userModel = us.queryLoginName(loginName, pwd);
        re.setResult(userModel);
        return re;
    }


    @RequestMapping("list")
    @ResponseBody
    @PermissionProxy(code = "4")
    public Map<String,Object> getUserList(UserQuery query){
        return us.getUserList(query);
    }

    @RequestMapping("toUpdateOrSavePage")
    public String toUpDateOrSavePage(Integer userId, Model model){
//        System.out.println("=================================="+userId);
        if(userId !=null ){
//            System.out.println(userId);
            User user = us.selectByPrimaryKey(userId);
            System.out.println(user);
            model.addAttribute("user",user);
        }
        return "user/add_update";
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultInfo addUser(User user,Integer[] roleIds){
        us.addUser(user,roleIds);
        return success("添加成功!");
    }

    @RequestMapping("updateUserMsg")
    @ResponseBody
    public ResultInfo updateUser(User user,Integer[] roleIds){
        us.updateUser(user,roleIds);
        return success("更新用户成功!");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteByUserId(Integer[] userId){
        System.out.println(Arrays.toString(userId));
        ResultInfo resultInfo=new ResultInfo();
        us.deleteUserById(userId);
        resultInfo.setCode(200);
        resultInfo.setMsg("删除成功!");
        return resultInfo;
    }


    //修改密码
    @ResponseBody
    @PostMapping("updatePwd")
    public ResultInfo updatePwd(HttpServletRequest req, String oldPwd, String newPwd, String confirmPwd) {
        ResultInfo re = new ResultInfo();
        int userid = LoginUserUtil.releaseUserIdFromCookie(req);
        //调用方法
        us.updatePassword(userid, oldPwd, newPwd, confirmPwd);
        return re;
    }



    //修改密码页面
    @RequestMapping("toPasswordPage")
    public String sayUpdate() {
        return "user/password";
    }

    //个人资料页面的跳转
    @RequestMapping("toSettingPage")
    public String setting(HttpServletRequest req, Model model) {
        int userid = LoginUserUtil.releaseUserIdFromCookie(req);
        User user = us.selectByPrimaryKey(userid);
        model.addAttribute("user", user);
        return "user/setting";

    }



    //修改个人资料
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(User user){

        us.changeUpdate(user);
        return success("修改成功");

    }
}
