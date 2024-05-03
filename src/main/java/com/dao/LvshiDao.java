package com.dao;

import com.entity.LvshiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.LvshiView;

/**
 * 律师 Dao 接口
 *
 * @author 
 */
public interface LvshiDao extends BaseMapper<LvshiEntity> {

   List<LvshiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
