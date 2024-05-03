package com.dao;

import com.entity.AnyuanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.AnyuanView;

/**
 * 案源信息 Dao 接口
 *
 * @author 
 */
public interface AnyuanDao extends BaseMapper<AnyuanEntity> {

   List<AnyuanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
