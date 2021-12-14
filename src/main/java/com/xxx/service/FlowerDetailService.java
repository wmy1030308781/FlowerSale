package com.xxx.service;

import com.xxx.base.BaseService;
import com.xxx.bean.BusCar;
import com.xxx.mapper.FlowerDetailMapper;
import com.xxx.query.FlowerDetailQuery;
import com.xxx.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class FlowerDetailService extends BaseService<BusCar,String> {

    @Autowired(required = false)
    private FlowerDetailMapper flowerDetailMapper;

    public List<BusCar> queryBusCarByParam(FlowerDetailQuery query){
        return  flowerDetailMapper.selectParams(query);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void save(BusCar busCar){
        //1.参数校验
        System.out.println(busCar.getFlowernumber()+"   "+busCar.getFlowertype()+"  "+busCar.getPrice());
        checkParams(busCar.getFlowernumber(), busCar.getFlowertype(), busCar.getPrice(), busCar.getDeposit());
        // 2.设置相关参数默认值
        busCar.setIssaleing(0);//0-未分配，1-已分配
        busCar.setCreatetime(new Date());

        //3.执行添加 判断结果
        AssertUtil.isTrue(flowerDetailMapper.insertSelective(busCar)<1,"添加失败了");
    }

    private void checkParams(String carnumber,String cartype,Double rentprice,Double deposit) {
        AssertUtil.isTrue(StringUtils.isBlank(carnumber),"花名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(cartype),"花的类型不能为空");
        AssertUtil.isTrue(rentprice==null,"售价不能为空");
        AssertUtil.isTrue(deposit==null,"库存不能为空");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void update(BusCar busCar){
        BusCar temp = flowerDetailMapper.selectByPrimaryKey(busCar.getFlowernumber());
        //判断
        System.out.println(temp);
        AssertUtil.isTrue(null==temp,"待修改记录不存在");
        //校验参数
        checkParams(busCar.getFlowernumber(), busCar.getFlowertype(), busCar.getPrice(), busCar.getDeposit());
        //3.执行更新 判断结果

        AssertUtil.isTrue(flowerDetailMapper.updateByPrimaryKeySelective(busCar)<1,"修改失败了");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeIds(String [] ids){
        //验证
        AssertUtil.isTrue(ids==null || ids.length==0,"请选择删除数据");
        //删除是否成功
        AssertUtil.isTrue(flowerDetailMapper.deleteBatch(ids)<1,"批量删除失败了");
    }

    public List<Map<String, Object>> findAllcars() {
        return flowerDetailMapper.selectAllFlowers();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void reset(BusCar busCar){
        if (busCar.getIssaleing() == 1) {
            flowerDetailMapper.reset(busCar);
            flowerDetailMapper.reset1(busCar);
        }else {
            AssertUtil.isTrue(busCar.getIssaleing() == 0,"该花暂未出售！");
        }
    }
}
