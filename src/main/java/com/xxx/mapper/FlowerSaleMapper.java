package com.xxx.mapper;

import com.xxx.base.BaseMapper;
import com.xxx.base.BaseQuery;
import com.xxx.bean.BusRent;
import org.springframework.dao.DataAccessException;

import java.awt.geom.Arc2D;
import java.util.List;

public interface FlowerSaleMapper extends BaseMapper<BusRent,Integer> {
    /**
     *新增加条件查询
     */
    public List<BusRent> selectParams(BaseQuery query);

    Integer deleteBatch(String[] Strings) throws DataAccessException;

    Integer updateFlowerIssaleing(String carnumber);

    BusRent selectByPrimaryKey(String string) throws DataAccessException;

    Double selectprice(String carnumber);
}