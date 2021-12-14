package com.xxx.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.base.BaseService;
import com.xxx.bean.BusRent;
import com.xxx.exceptions.ParamsException;
import com.xxx.mapper.FlowerCustomerMapper;
import com.xxx.mapper.FlowerSaleMapper;
import com.xxx.query.BusRentQuery;
import com.xxx.utils.AssertUtil;

import java.text.SimpleDateFormat;
import java.util.*;

import com.xxx.utils.LoginUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Service
public class FlowerSaleService extends BaseService<BusRent,Integer> {
    @Autowired(required = false)
    private FlowerSaleMapper flowerSaleMapper;

    @Autowired(required = false)
    private FlowerCustomerMapper flowerCustomerMapper;

    /**
     * 查询所有
     * */
    public Map<String,Object> findall(BusRentQuery busRent){
        //实例化map
        Map<String,Object> map= new HashMap<>();
        //开启分页
        PageHelper.startPage(busRent.getPage(),busRent.getLimit());
        //调用方法查询所有的信息
        List<BusRent> slist = flowerSaleMapper.selectParams(busRent);
        //开始分页
        PageInfo<BusRent> plist = new PageInfo<BusRent>(slist);
        //准备数据
        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", plist.getTotal());
        map.put("data", plist.getList());
        //返回目标map
        return map;
    }

    /****
     * 订单数据添加
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(BusRent busRent, Integer time, HttpServletRequest req){
        //1.参数校验
        checkParams(busRent.getSaleid(),busRent.getIdentity(),busRent.getFlowernumber());
        // 2.设置相关参数默认值
        busRent.setSaleflag(0);//0-未分配，1-已分配

        busRent.setSaleid(makeDh());

        busRent.setCreatetime(new Date());
        busRent.setOpername(LoginUserUtil.releaseLoginNameFromCookie(req));

        busRent.setBegindate(new Date());

        busRent.setSaledate(addAndSubtractDaysByGetTime(new Date(),time));

        busRent.setPrice(flowerSaleMapper.selectprice(busRent.getFlowernumber()));

        flowerSaleMapper.updateFlowerIssaleing(busRent.getFlowernumber());
        //3.执行添加 判断结果
        AssertUtil.isTrue(flowerSaleMapper.insertSelective(busRent)<1,"添加失败了");
    }

    /**
     * 验证数据
     */
    private void checkParams(String rentid, String identity, String carnumber) {
        AssertUtil.isTrue(StringUtils.isBlank(identity),"身份证号不能为空");
        List<Map<String, Object>> list = flowerCustomerMapper.findAllidentity();
        boolean flag=true;
        System.out.println(identity);
        for(int i=0;i<list.size();i++){
            if (identity.equals(list.get(i).get("identity"))){
                System.out.println(list.get(i).get("identity"));
                flag=false;
            }
        }
        if (flag){
            throw new ParamsException("没有该用户");
        }
    }

    /****
     * 订单数据删除
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeIds(String [] rentids){
        //验证
        AssertUtil.isTrue(rentids==null || rentids.length==0,"请选择删除数据");
        //删除是否成功
        System.out.println(rentids[0].toString()+"要删除的东西");
        AssertUtil.isTrue(flowerSaleMapper.deleteBatch(rentids)<1,"批量删除失败了");
    }

    /**
     * 返回日期处理方法
     * @param dateTime
     * @param n
     * @return
     */
    public static Date addAndSubtractDaysByGetTime(Date dateTime/*待处理的日期*/,int n/*加减天数*/){

        //日期格式
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(df.format(new Date(dateTime.getTime() + n * 24 * 60 * 60 * 1000L)));
        //System.out.println(dd.format(new Date(dateTime.getTime() + n * 24 * 60 * 60 * 1000L)));
        //注意这里一定要转换成Long类型，要不n超过25时会出现范围溢出，从而得不到想要的日期值
        return new Date(dateTime.getTime() + n * 24 * 60 * 60 * 1000L);
    }

    public BusRent selectByPrimaryKey(String string){
        return flowerSaleMapper.selectByPrimaryKey(string);
    }

    /***
     * 生成订单号
     */
    public String makeDh(){
        Date date = new Date();
        String strDateFormat = "yyyyMMdd";
        Date date1 = new Date();
        String strDateFormat1 =  "HHmmss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        SimpleDateFormat sdf1 = new SimpleDateFormat(strDateFormat1);
        Random random = new Random();
        int i = random.nextInt(9999);
        int a = random.nextInt(9999);
        String dh="CZ_"+sdf.format(date)+"_"+sdf1.format(date1)+"_"+i+"_"+a;
        return dh;
    }
}
