package com.xxx.mapper;

import com.xxx.base.BaseMapper;
import com.xxx.bean.BusCar;
import com.xxx.query.FlowerDetailQuery;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface FlowerDetailMapper extends BaseMapper<BusCar,String> {
    public List<BusCar> selectParams(FlowerDetailQuery query);
    @MapKey("")
    public List<Map<String,Object>> selectAllFlowers();
    public void reset(BusCar busCar);
    public void reset1(BusCar busCar);
}