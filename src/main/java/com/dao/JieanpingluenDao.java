package com.dao;

import com.entity.JieanpingluenEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JieanpingluenView;

/**
 * 结案评论 Dao 接口
 *
 * @author 
 */
public interface JieanpingluenDao extends BaseMapper<JieanpingluenEntity> {

   List<JieanpingluenView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
