package com.dao;

import com.entity.LvshiCommentbackEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.LvshiCommentbackView;

/**
 * 律师意见 Dao 接口
 *
 * @author 
 */
public interface LvshiCommentbackDao extends BaseMapper<LvshiCommentbackEntity> {

   List<LvshiCommentbackView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
