
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
 * 律师
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/lvshi")
public class LvshiController {
    private static final Logger logger = LoggerFactory.getLogger(LvshiController.class);

    private static final String TABLE_NAME = "lvshi";

    @Autowired
    private LvshiService lvshiService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private AnyuanService anyuanService;//案源信息
    @Autowired
    private AnyuanYuyueService anyuanYuyueService;//案件信息
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private JieanpingluenService jieanpingluenService;//结案评论
    @Autowired
    private KehuyijianService kehuyijianService;//事务所意见
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
        params.put("lvshiDeleteStart",1);params.put("lvshiDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = lvshiService.queryPage(params);

        //字典表数据转换
        List<LvshiView> list =(List<LvshiView>)page.getList();
        for(LvshiView c:list){
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
        LvshiEntity lvshi = lvshiService.selectById(id);
        if(lvshi !=null){
            //entity转view
            LvshiView view = new LvshiView();
            BeanUtils.copyProperties( lvshi , view );//把实体数据重构到view中
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
    public R save(@RequestBody LvshiEntity lvshi, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,lvshi:{}",this.getClass().getName(),lvshi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<LvshiEntity> queryWrapper = new EntityWrapper<LvshiEntity>()
            .eq("username", lvshi.getUsername())
            .or()
            .eq("lvshi_phone", lvshi.getLvshiPhone())
            .or()
            .eq("lvshi_id_number", lvshi.getLvshiIdNumber())
            .andNew()
            .eq("lvshi_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        LvshiEntity lvshiEntity = lvshiService.selectOne(queryWrapper);
        if(lvshiEntity==null){
            lvshi.setLvshiDelete(1);
            lvshi.setCreateTime(new Date());
            lvshi.setPassword("123456");
            lvshiService.insert(lvshi);
            return R.ok();
        }else {
            return R.error(511,"账户或者律师手机号或者律师身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody LvshiEntity lvshi, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,lvshi:{}",this.getClass().getName(),lvshi.toString());
        LvshiEntity oldLvshiEntity = lvshiService.selectById(lvshi.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(lvshi.getLvshiPhoto()) || "null".equals(lvshi.getLvshiPhoto())){
                lvshi.setLvshiPhoto(null);
        }

            lvshiService.updateById(lvshi);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<LvshiEntity> oldLvshiList =lvshiService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<LvshiEntity> list = new ArrayList<>();
        for(Integer id:ids){
            LvshiEntity lvshiEntity = new LvshiEntity();
            lvshiEntity.setId(id);
            lvshiEntity.setLvshiDelete(2);
            list.add(lvshiEntity);
        }
        if(list != null && list.size() >0){
            lvshiService.updateBatchById(list);
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
            List<LvshiEntity> lvshiList = new ArrayList<>();//上传的东西
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
                            LvshiEntity lvshiEntity = new LvshiEntity();
//                            lvshiEntity.setUsername(data.get(0));                    //账户 要改的
//                            //lvshiEntity.setPassword("123456");//密码
//                            lvshiEntity.setLvshiName(data.get(0));                    //律师名称 要改的
//                            lvshiEntity.setLvshiPhoto("");//详情和图片
//                            lvshiEntity.setLvshiPhone(data.get(0));                    //律师手机号 要改的
//                            lvshiEntity.setLvshiIdNumber(data.get(0));                    //律师身份证号 要改的
//                            lvshiEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            lvshiEntity.setLvshiEmail(data.get(0));                    //邮箱 要改的
//                            lvshiEntity.setLvshiDelete(1);//逻辑删除字段
//                            lvshiEntity.setCreateTime(date);//时间
                            lvshiList.add(lvshiEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //律师手机号
                                if(seachFields.containsKey("lvshiPhone")){
                                    List<String> lvshiPhone = seachFields.get("lvshiPhone");
                                    lvshiPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> lvshiPhone = new ArrayList<>();
                                    lvshiPhone.add(data.get(0));//要改的
                                    seachFields.put("lvshiPhone",lvshiPhone);
                                }
                                //律师身份证号
                                if(seachFields.containsKey("lvshiIdNumber")){
                                    List<String> lvshiIdNumber = seachFields.get("lvshiIdNumber");
                                    lvshiIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> lvshiIdNumber = new ArrayList<>();
                                    lvshiIdNumber.add(data.get(0));//要改的
                                    seachFields.put("lvshiIdNumber",lvshiIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<LvshiEntity> lvshiEntities_username = lvshiService.selectList(new EntityWrapper<LvshiEntity>().in("username", seachFields.get("username")).eq("lvshi_delete", 1));
                        if(lvshiEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(LvshiEntity s:lvshiEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //律师手机号
                        List<LvshiEntity> lvshiEntities_lvshiPhone = lvshiService.selectList(new EntityWrapper<LvshiEntity>().in("lvshi_phone", seachFields.get("lvshiPhone")).eq("lvshi_delete", 1));
                        if(lvshiEntities_lvshiPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(LvshiEntity s:lvshiEntities_lvshiPhone){
                                repeatFields.add(s.getLvshiPhone());
                            }
                            return R.error(511,"数据库的该表中的 [律师手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //律师身份证号
                        List<LvshiEntity> lvshiEntities_lvshiIdNumber = lvshiService.selectList(new EntityWrapper<LvshiEntity>().in("lvshi_id_number", seachFields.get("lvshiIdNumber")).eq("lvshi_delete", 1));
                        if(lvshiEntities_lvshiIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(LvshiEntity s:lvshiEntities_lvshiIdNumber){
                                repeatFields.add(s.getLvshiIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [律师身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        lvshiService.insertBatch(lvshiList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }

    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        LvshiEntity lvshi = lvshiService.selectOne(new EntityWrapper<LvshiEntity>().eq("username", username));
        if(lvshi==null || !lvshi.getPassword().equals(password))
            return R.error("账号或密码不正确");
        else if(lvshi.getLvshiDelete() != 1)
            return R.error("账户已被删除");
        String token = tokenService.generateToken(lvshi.getId(),username, "lvshi", "律师");
        R r = R.ok();
        r.put("token", token);
        r.put("role","律师");
        r.put("username",lvshi.getLvshiName());
        r.put("tableName","lvshi");
        r.put("userId",lvshi.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody LvshiEntity lvshi, HttpServletRequest request) {
//    	ValidatorUtils.validateEntity(user);
        Wrapper<LvshiEntity> queryWrapper = new EntityWrapper<LvshiEntity>()
            .eq("username", lvshi.getUsername())
            .or()
            .eq("lvshi_phone", lvshi.getLvshiPhone())
            .or()
            .eq("lvshi_id_number", lvshi.getLvshiIdNumber())
            .andNew()
            .eq("lvshi_delete", 1)
            ;
        LvshiEntity lvshiEntity = lvshiService.selectOne(queryWrapper);
        if(lvshiEntity != null)
            return R.error("账户或者律师手机号或者律师身份证号已经被使用");
        lvshi.setLvshiDelete(1);
        lvshi.setCreateTime(new Date());
        lvshiService.insert(lvshi);

        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id, HttpServletRequest request) {
        LvshiEntity lvshi = lvshiService.selectById(id);
        lvshi.setPassword("123456");
        lvshiService.updateById(lvshi);
        return R.ok();
    }

	/**
	 * 修改密码
	 */
	@GetMapping(value = "/updatePassword")
	public R updatePassword(String  oldPassword, String  newPassword, HttpServletRequest request) {
        LvshiEntity lvshi = lvshiService.selectById((Integer)request.getSession().getAttribute("userId"));
		if(newPassword == null){
			return R.error("新密码不能为空") ;
		}
		if(!oldPassword.equals(lvshi.getPassword())){
			return R.error("原密码输入错误");
		}
		if(newPassword.equals(lvshi.getPassword())){
			return R.error("新密码不能和原密码一致") ;
		}
        lvshi.setPassword(newPassword);
		lvshiService.updateById(lvshi);
		return R.ok();
	}



    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        LvshiEntity lvshi = lvshiService.selectOne(new EntityWrapper<LvshiEntity>().eq("username", username));
        if(lvshi!=null){
            lvshi.setPassword("123456");
            lvshiService.updateById(lvshi);
            return R.ok();
        }else{
           return R.error("账号不存在");
        }
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrLvshi(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        LvshiEntity lvshi = lvshiService.selectById(id);
        if(lvshi !=null){
            //entity转view
            LvshiView view = new LvshiView();
            BeanUtils.copyProperties( lvshi , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }



}

