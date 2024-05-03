
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
 * 案源信息
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/anyuan")
public class AnyuanController {
    private static final Logger logger = LoggerFactory.getLogger(AnyuanController.class);

    private static final String TABLE_NAME = "anyuan";

    @Autowired
    private AnyuanService anyuanService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private AnyuanYuyueService anyuanYuyueService;//案件信息
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
        params.put("anyuanDeleteStart",1);params.put("anyuanDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = anyuanService.queryPage(params);

        //字典表数据转换
        List<AnyuanView> list =(List<AnyuanView>)page.getList();
        for(AnyuanView c:list){
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
        AnyuanEntity anyuan = anyuanService.selectById(id);
        if(anyuan !=null){
            //entity转view
            AnyuanView view = new AnyuanView();
            BeanUtils.copyProperties( anyuan , view );//把实体数据重构到view中
            //级联表 客户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(anyuan.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
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
    public R save(@RequestBody AnyuanEntity anyuan, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,anyuan:{}",this.getClass().getName(),anyuan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("客户".equals(role))
            anyuan.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<AnyuanEntity> queryWrapper = new EntityWrapper<AnyuanEntity>()
            .eq("anyuan_name", anyuan.getAnyuanName())
            .eq("anyuan_types", anyuan.getAnyuanTypes())
            .eq("yonghu_id", anyuan.getYonghuId())
            .in("anyuan_yesno_types", new Integer[]{1,2})
            .eq("anyuan_delete", anyuan.getAnyuanDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        AnyuanEntity anyuanEntity = anyuanService.selectOne(queryWrapper);
        if(anyuanEntity==null){
            anyuan.setAnyuanYesnoTypes(1);
            anyuan.setAnyuanDelete(1);
            anyuan.setInsertTime(new Date());
            anyuan.setCreateTime(new Date());
            anyuanService.insert(anyuan);
            return R.ok();
        }else {
            if(anyuanEntity.getAnyuanYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(anyuanEntity.getAnyuanYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody AnyuanEntity anyuan, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,anyuan:{}",this.getClass().getName(),anyuan.toString());
        AnyuanEntity oldAnyuanEntity = anyuanService.selectById(anyuan.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("客户".equals(role))
//            anyuan.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            anyuanService.updateById(anyuan);//根据id更新
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody AnyuanEntity anyuanEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,anyuanEntity:{}",this.getClass().getName(),anyuanEntity.toString());

        AnyuanEntity oldAnyuan = anyuanService.selectById(anyuanEntity.getId());//查询原先数据

        if(anyuanEntity.getAnyuanYesnoTypes() == 2){//通过
            AnyuanYuyueEntity anyuanYuyueEntity = new AnyuanYuyueEntity();
            anyuanYuyueEntity.setLvshiId((Integer)request.getSession().getAttribute("userId"));
            anyuanYuyueEntity.setAnyuanYuyueYesnoTypes(1);
            anyuanYuyueEntity.setCreateTime(new Date());
            anyuanYuyueEntity.setInsertTime(new Date());
            anyuanYuyueEntity.setAnyuanId(oldAnyuan.getId());
            anyuanYuyueService.insert(anyuanYuyueEntity);
        }
        anyuanService.updateById(anyuanEntity);//审核

        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<AnyuanEntity> oldAnyuanList =anyuanService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<AnyuanEntity> list = new ArrayList<>();
        for(Integer id:ids){
            AnyuanEntity anyuanEntity = new AnyuanEntity();
            anyuanEntity.setId(id);
            anyuanEntity.setAnyuanDelete(2);
            list.add(anyuanEntity);
        }
        if(list != null && list.size() >0){
            anyuanService.updateBatchById(list);
        }

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
            List<AnyuanEntity> anyuanList = new ArrayList<>();//上传的东西
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
                            AnyuanEntity anyuanEntity = new AnyuanEntity();
//                            anyuanEntity.setAnyuanUuidNumber(data.get(0));                    //案源编号 要改的
//                            anyuanEntity.setAnyuanName(data.get(0));                    //案源名称 要改的
//                            anyuanEntity.setAnyuanTypes(Integer.valueOf(data.get(0)));   //案源类型 要改的
//                            anyuanEntity.setYonghuId(Integer.valueOf(data.get(0)));   //客户 要改的
//                            anyuanEntity.setAnyuanContent("");//详情和图片
//                            anyuanEntity.setAnyuanYesnoTypes(Integer.valueOf(data.get(0)));   //收案审批 要改的
//                            anyuanEntity.setAnyuanYesnoText(data.get(0));                    //审批回复 要改的
//                            anyuanEntity.setAnyuanDelete(1);//逻辑删除字段
//                            anyuanEntity.setInsertTime(date);//时间
//                            anyuanEntity.setCreateTime(date);//时间
                            anyuanList.add(anyuanEntity);


                            //把要查询是否重复的字段放入map中
                                //案源编号
                                if(seachFields.containsKey("anyuanUuidNumber")){
                                    List<String> anyuanUuidNumber = seachFields.get("anyuanUuidNumber");
                                    anyuanUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> anyuanUuidNumber = new ArrayList<>();
                                    anyuanUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("anyuanUuidNumber",anyuanUuidNumber);
                                }
                        }

                        //查询是否重复
                         //案源编号
                        List<AnyuanEntity> anyuanEntities_anyuanUuidNumber = anyuanService.selectList(new EntityWrapper<AnyuanEntity>().in("anyuan_uuid_number", seachFields.get("anyuanUuidNumber")).eq("anyuan_delete", 1));
                        if(anyuanEntities_anyuanUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(AnyuanEntity s:anyuanEntities_anyuanUuidNumber){
                                repeatFields.add(s.getAnyuanUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [案源编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        anyuanService.insertBatch(anyuanList);
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

