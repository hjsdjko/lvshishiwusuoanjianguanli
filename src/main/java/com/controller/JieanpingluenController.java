
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 结案评论
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jieanpingluen")
public class JieanpingluenController {
    private static final Logger logger = LoggerFactory.getLogger(JieanpingluenController.class);

    private static final String TABLE_NAME = "jieanpingluen";

    @Autowired
    private JieanpingluenService jieanpingluenService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private AnyuanService anyuanService;//案源信息
    @Autowired
    private AnyuanYuyueService anyuanYuyueService;//案件信息
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private KehuyijianService kehuyijianService;//事务所意见
    @Autowired
    private LvshiService lvshiService;//律师
    @Autowired
    private LvshiCommentbackService lvshiCommentbackService;//律师意见
    @Autowired
    private NewsService newsService;//公告信息
    @Autowired
    private YonghuService yonghuService;//客户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("客户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("律师".equals(role))
            params.put("lvshiId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = jieanpingluenService.queryPage(params);

        //字典表数据转换
        List<JieanpingluenView> list =(List<JieanpingluenView>)page.getList();
        for(JieanpingluenView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JieanpingluenEntity jieanpingluen = jieanpingluenService.selectById(id);
        if(jieanpingluen !=null){
            //entity转view
            JieanpingluenView view = new JieanpingluenView();
            BeanUtils.copyProperties( jieanpingluen , view );//把实体数据重构到view中
            //级联表 案源信息
            //级联表
            AnyuanEntity anyuan = anyuanService.selectById(jieanpingluen.getAnyuanId());
            if(anyuan != null){
            BeanUtils.copyProperties( anyuan , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setAnyuanId(anyuan.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody JieanpingluenEntity jieanpingluen, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jieanpingluen:{}",this.getClass().getName(),jieanpingluen.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<JieanpingluenEntity> queryWrapper = new EntityWrapper<JieanpingluenEntity>()
            .eq("anyuan_id", jieanpingluen.getAnyuanId())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JieanpingluenEntity jieanpingluenEntity = jieanpingluenService.selectOne(queryWrapper);
        if(jieanpingluenEntity==null){
            jieanpingluen.setInsertTime(new Date());
            jieanpingluen.setCreateTime(new Date());
            jieanpingluenService.insert(jieanpingluen);
            return R.ok();
        }else {
            return R.error(511,"请不要重复发表评论");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JieanpingluenEntity jieanpingluen, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,jieanpingluen:{}",this.getClass().getName(),jieanpingluen.toString());
        JieanpingluenEntity oldJieanpingluenEntity = jieanpingluenService.selectById(jieanpingluen.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");

            jieanpingluenService.updateById(jieanpingluen);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<JieanpingluenEntity> oldJieanpingluenList =jieanpingluenService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        jieanpingluenService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<JieanpingluenEntity> jieanpingluenList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            JieanpingluenEntity jieanpingluenEntity = new JieanpingluenEntity();
//                            jieanpingluenEntity.setAnyuanId(Integer.valueOf(data.get(0)));   //案源 要改的
//                            jieanpingluenEntity.setJieanpingluenText(data.get(0));                    //评论内容 要改的
//                            jieanpingluenEntity.setInsertTime(date);//时间
//                            jieanpingluenEntity.setCreateTime(date);//时间
                            jieanpingluenList.add(jieanpingluenEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        jieanpingluenService.insertBatch(jieanpingluenList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




}

