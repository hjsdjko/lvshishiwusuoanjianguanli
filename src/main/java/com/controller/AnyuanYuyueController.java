
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
 * 案件信息
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/anyuanYuyue")
public class AnyuanYuyueController {
    private static final Logger logger = LoggerFactory.getLogger(AnyuanYuyueController.class);

    private static final String TABLE_NAME = "anyuanYuyue";

    @Autowired
    private AnyuanYuyueService anyuanYuyueService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private AnyuanService anyuanService;//案源信息
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private JieanpingluenService jieanpingluenService;//结案评论
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
        PageUtils page = anyuanYuyueService.queryPage(params);

        //字典表数据转换
        List<AnyuanYuyueView> list =(List<AnyuanYuyueView>)page.getList();
        for(AnyuanYuyueView c:list){
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
        AnyuanYuyueEntity anyuanYuyue = anyuanYuyueService.selectById(id);
        if(anyuanYuyue !=null){
            //entity转view
            AnyuanYuyueView view = new AnyuanYuyueView();
            BeanUtils.copyProperties( anyuanYuyue , view );//把实体数据重构到view中
            //级联表 律师
            //级联表
            LvshiEntity lvshi = lvshiService.selectById(anyuanYuyue.getLvshiId());
            if(lvshi != null){
            BeanUtils.copyProperties( lvshi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "lvshiId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setLvshiId(lvshi.getId());
            }
            //级联表 案源信息
            //级联表
            AnyuanEntity anyuan = anyuanService.selectById(anyuanYuyue.getAnyuanId());
            if(anyuan != null){
            BeanUtils.copyProperties( anyuan , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "lvshiId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
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
    public R save(@RequestBody AnyuanYuyueEntity anyuanYuyue, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,anyuanYuyue:{}",this.getClass().getName(),anyuanYuyue.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("律师".equals(role))
            anyuanYuyue.setLvshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<AnyuanYuyueEntity> queryWrapper = new EntityWrapper<AnyuanYuyueEntity>()
            .eq("anyuan_id", anyuanYuyue.getAnyuanId())
            .eq("lvshi_id", anyuanYuyue.getLvshiId())
            .eq("anyuan_yuyue_types", anyuanYuyue.getAnyuanYuyueTypes())
            .in("anyuan_yuyue_yesno_types", new Integer[]{1,2})
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        AnyuanYuyueEntity anyuanYuyueEntity = anyuanYuyueService.selectOne(queryWrapper);
        if(anyuanYuyueEntity==null){
            anyuanYuyue.setAnyuanYuyueYesnoTypes(1);
            anyuanYuyue.setInsertTime(new Date());
            anyuanYuyue.setCreateTime(new Date());
            anyuanYuyueService.insert(anyuanYuyue);
            return R.ok();
        }else {
            if(anyuanYuyueEntity.getAnyuanYuyueYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(anyuanYuyueEntity.getAnyuanYuyueYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody AnyuanYuyueEntity anyuanYuyue, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,anyuanYuyue:{}",this.getClass().getName(),anyuanYuyue.toString());
        AnyuanYuyueEntity oldAnyuanYuyueEntity = anyuanYuyueService.selectById(anyuanYuyue.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));

        if(anyuanYuyue.getAnyuanYuyueYesnoTypes() == 3){
            anyuanYuyue.setAnyuanYuyueYesnoTypes(1);
        }

        if("".equals(anyuanYuyue.getAnyuanYuyueFile()) || "null".equals(anyuanYuyue.getAnyuanYuyueFile())){
                anyuanYuyue.setAnyuanYuyueFile(null);
        }

            anyuanYuyueService.updateById(anyuanYuyue);//根据id更新
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody AnyuanYuyueEntity anyuanYuyueEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,anyuanYuyueEntity:{}",this.getClass().getName(),anyuanYuyueEntity.toString());

        AnyuanYuyueEntity oldAnyuanYuyue = anyuanYuyueService.selectById(anyuanYuyueEntity.getId());//查询原先数据

//        if(anyuanYuyueEntity.getAnyuanYuyueYesnoTypes() == 2){//通过
//            anyuanYuyueEntity.setAnyuanYuyueTypes();
//        }else if(anyuanYuyueEntity.getAnyuanYuyueYesnoTypes() == 3){//拒绝
//            anyuanYuyueEntity.setAnyuanYuyueTypes();
//        }
        anyuanYuyueEntity.setAnyuanYuyueShenheTime(new Date());//审核时间
        anyuanYuyueService.updateById(anyuanYuyueEntity);//审核

        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<AnyuanYuyueEntity> oldAnyuanYuyueList =anyuanYuyueService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        anyuanYuyueService.deleteBatchIds(Arrays.asList(ids));

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
            List<AnyuanYuyueEntity> anyuanYuyueList = new ArrayList<>();//上传的东西
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
                            AnyuanYuyueEntity anyuanYuyueEntity = new AnyuanYuyueEntity();
//                            anyuanYuyueEntity.setAnyuanId(Integer.valueOf(data.get(0)));   //案源 要改的
//                            anyuanYuyueEntity.setLvshiId(Integer.valueOf(data.get(0)));   //律师 要改的
//                            anyuanYuyueEntity.setAnyuanYuyueText(data.get(0));                    //案源分析 要改的
//                            anyuanYuyueEntity.setLiuchengText(data.get(0));                    //流程 要改的
//                            anyuanYuyueEntity.setJiluText(data.get(0));                    //交接记录 要改的
//                            anyuanYuyueEntity.setJingfeiPrice(data.get(0));                    //办案经费 要改的
//                            anyuanYuyueEntity.setAnyuanYuyueFile(data.get(0));                    //材料 要改的
//                            anyuanYuyueEntity.setAnyuanYuyueTypes(Integer.valueOf(data.get(0)));   //案件类型 要改的
//                            anyuanYuyueEntity.setAnyuanYuyueYesnoTypes(Integer.valueOf(data.get(0)));   //结案审批 要改的
//                            anyuanYuyueEntity.setAnyuanYuyueYesnoText(data.get(0));                    //审批回复 要改的
//                            anyuanYuyueEntity.setAnyuanYuyueShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            anyuanYuyueEntity.setInsertTime(date);//时间
//                            anyuanYuyueEntity.setCreateTime(date);//时间
                            anyuanYuyueList.add(anyuanYuyueEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        anyuanYuyueService.insertBatch(anyuanYuyueList);
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

