package com.xxx.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.base.BaseService;
import com.xxx.bean.LogLogin;
import com.xxx.bean.RoleUser;
import com.xxx.bean.User;
import com.xxx.mapper.LogLoginMapper;
import com.xxx.mapper.RoleMenuMapper;
import com.xxx.mapper.RoleUserMapper;
import com.xxx.mapper.UserMapper;
import com.xxx.model.UserModel;
import com.xxx.query.UserQuery;
import com.xxx.utils.AssertUtil;
import com.xxx.utils.Md5Util;
import com.xxx.utils.PhoneUtil;
import com.xxx.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserService extends BaseService<User,Integer> {

    @Resource
    private UserMapper us;

    @Resource
    private RoleUserMapper rum;

    @Resource
    private RoleMenuMapper rmm;

    @Resource
    private LogLoginMapper logLoginMapper;


    public UserModel queryLoginName(String loginName, String pwd) {
        //1.验证参数
        checkLoginParams(loginName, pwd);
        //2,查询对象
        User user = us.selectUserByLoginName(loginName);
        //3.
        AssertUtil.isTrue(user == null, "用户不存在或者已经注销");
        //4.用户存在,密码的校验
        checkLoginPwd(pwd, user.getPwd());
        LogLogin logLogin = new LogLogin();
        logLogin.setLoginname(user.getRealname() + "-" + user.getLoginname());
        logLogin.setLoginip("127.1.1.1.1");
        logLogin.setLogintime(new Date());
        logLoginMapper.insert(logLogin);


        //5.返回用户信息
        return modelUser(user);


    }

    //返回model
    private UserModel modelUser(User user) {
        //实例化对象
        UserModel um = new UserModel();
        um.setLoginname(user.getLoginname());
        um.setRealname(user.getRealname());
        um.setUserid(UserIDBase64.encoderUserID(user.getUserid()));
        return um;

    }

    //密码校验
    private void checkLoginPwd(String pwd, String pwd1) {
        //加密比较

        pwd = Md5Util.encode(pwd);

        //比较
        AssertUtil.isTrue(!(pwd.equals(pwd1)), "输入密码不正确");
    }

    //参数校验
    private void checkLoginParams(String loginName, String pwd) {
        AssertUtil.isTrue(StringUtils.isBlank(loginName), "用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(pwd), "密码不能为空");


    }


    /**
     * 获取用户的列表
     * @param userQuery
     * @return
     */
    public Map<String,Object> getUserList(UserQuery userQuery){
        List<User> userList = us.getUserList(userQuery);
        Map<String,Object> map=new HashMap<>();
        PageHelper.startPage(userQuery.getPage(),userQuery.getLimit());
        PageInfo<User> plist=new PageInfo<>(userList);
        map.put("code",0);
        map.put("msg","success");
        map.put("count", plist.getTotal());
        map.put("data",plist.getList());
        return map;
    }

    /**
     * 用户添加
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(User user,Integer[] roleIds){
        checkParam(user);
        user.setPwd(Md5Util.encode("123456"));
        user.setAvailable(1);
        AssertUtil.isTrue(us.insertHasKey(user)<1,"添加用户失败!");

        addRoleOfUser(user.getUserid(),roleIds);
    }

    /**
     * 用户角色的添加和更新
     * @param userId
     * @param roleIds
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRoleOfUser(Integer userId,Integer[] roleIds){
        AssertUtil.isTrue(roleIds==null||roleIds.length==0,"请为用户选择角色");
        Integer[] nums = rum.findRolesByUserId(userId);
        List<RoleUser> list=new ArrayList<>();
        if(nums != null){
            //删除原有角色
            AssertUtil.isTrue(rum.deleteByUserid(userId)!=nums.length,"删除角色原有权限失败!");
        }

        for (Integer n:roleIds) {
            RoleUser roleUser=new RoleUser();
            roleUser.setRid(n);
            roleUser.setUid(userId);
            list.add(roleUser);
        }
        AssertUtil.isTrue(rum.insertMsg(list)!=roleIds.length,"用户角色添加失败!");

    }

    /**
     * 添加用户的参数校验
     * @param user
     */
    private void checkParam(User user){
        AssertUtil.isTrue(StringUtils.isBlank(user.getRealname()),"用户姓名不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(user.getLoginname()),"登录名不能为空!");
        User temp = us.getUserByName(user.getLoginname());
//        System.out.println(user.getUserid());
        AssertUtil.isTrue(temp!=null&&!(temp.getUserid().equals(user.getUserid())),"该用户名已存在!");

        AssertUtil.isTrue(user.getIdentity()==null,"身份证号不能为空!");
        AssertUtil.isTrue(user.getPhone()==null|| !(PhoneUtil.isMobile(user.getPhone())),"手机号不符合要求!");
        AssertUtil.isTrue(user.getAddress()==null,"地址不能为空!");
        AssertUtil.isTrue(user.getSex()==null,"请选择性别!");
    }

    /**
     * 更新用户信息
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(User user,Integer[] roleIds){
        checkParam(user);
        AssertUtil.isTrue(us.updateByPrimaryKeySelective(user)<1,"用户信息更新失败!");
        addRoleOfUser(user.getUserid(),roleIds);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUserById(Integer[] ids){
        AssertUtil.isTrue(ids==null||ids.length==0,"请选择要删除的行");
        AssertUtil.isTrue(us.deleteUserById(ids)!=ids.length,"删除用户失败!");

        rum.deleteByManyUserid(ids);
    }

    /**
     * 查找用户的权限
     * @param userId
     * @return
     */
    public List<Integer> getMenuOfUser(Integer userId){
        //找到用户所拥有的角色id
//        Integer[] roles = rum.findRolesByUserId(userId);
        return rmm.getMenuByManyRoleId(userId);
    }



    /*修改密码
     * 1)userId 存在，user对象  登录
     * 2)原始密码，非空，和密文密码一致
     * 3）新密码，非空，不能和原始密码一致
     * 4）确认密码 非空，确认密码和新密码一致
     * 5）修改密码，加密
     * 6）修改成功与否，<1;
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePassword(Integer userId, String oldPwd, String newPwd, String confirmPwd) {
        //根据用户UserId拿到当前对象
        User user = us.selectByPrimaryKey(userId);
        //1.参数校验
        checkPwdParam(user, oldPwd, newPwd, confirmPwd);
        //2.修改密码加密
        user.setPwd(Md5Util.encode(newPwd));
        //3.修改是否成功
        AssertUtil.isTrue(us.updateByPrimaryKeySelective(user) < 1, "修改失败了");
    }

    /**
     * 验证参数列表
     *
     * @param user
     * @param oldPwd
     * @param newPwd
     * @param confirmPwd
     */
    private void checkPwdParam(User user, String oldPwd, String newPwd, String confirmPwd) {
        //1,user是否存在
        AssertUtil.isTrue(null == user, "用户未登录或者不存在");
        //判空
        AssertUtil.isTrue(StringUtils.isBlank(oldPwd), "请输入原始密码");
        //和登录密码一致
        AssertUtil.isTrue(!(user.getPwd().equals(Md5Util.encode(oldPwd))), "原始密码不正确");
        //新密码非空，不能和原始密码一致
        AssertUtil.isTrue(StringUtils.isBlank(newPwd), "请输入新密码");
        //不能和原始密码一致
        AssertUtil.isTrue(newPwd.equals(oldPwd), "新和原始不能一致");
        //确认密码非空，新密码和确认密码一致
        AssertUtil.isTrue(StringUtils.isBlank(confirmPwd), "确认密码不能为空");
        //新密码和确认密码一致
        AssertUtil.isTrue(!newPwd.equals(confirmPwd), "新密码和确认密码必须一致");
    }


    /**
     * 修改用户和个人资料
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void changeUpdate(User user) {
        // 1. 参数校验
        // 1. 参数校验
        checkUserParams(user.getPhone(),user.getRealname(),user.getIdentity());

        // 通过id查询⽤户对象
        User tp = us.selectUserByLoginName(user.getLoginname());
        System.out.println(tp);
        System.out.println(user);
        //用户是否存在
        AssertUtil.isTrue(tp == null, "修改记录不存在");
        AssertUtil.isTrue(tp == null && !(tp.getUserid().equals(user.getUserid())), "用户已存在");

        //设置默认值
        user.setAvailable(1);
        //执行
        AssertUtil.isTrue(us.updateByPrimaryKeySelective(user) < 1, "修改失败");


    }

    /**
     * 参数校验
     * @param phone
     * @param realname
     * @param identity
     */
    private void checkUserParams(String phone, String realname, String identity) {
        AssertUtil.isTrue(StringUtils.isBlank(realname),"请输入真实姓名");
        //手机号
        AssertUtil.isTrue(StringUtils.isBlank(phone), "请输入手机号");
        //手机格式
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone), "请输入合法的手机号");
        //身份证
        AssertUtil.isTrue(StringUtils.isBlank(identity), "身份证不能为空");


    }

}
