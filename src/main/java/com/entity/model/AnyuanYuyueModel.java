package com.entity.model;

import com.entity.AnyuanYuyueEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 案件信息
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class AnyuanYuyueModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 案源
     */
    private Integer anyuanId;


    /**
     * 律师
     */
    private Integer lvshiId;


    /**
     * 案源分析
     */
    private String anyuanYuyueText;


    /**
     * 流程
     */
    private String liuchengText;


    /**
     * 交接记录
     */
    private String jiluText;


    /**
     * 办案经费
     */
    private Double jingfeiPrice;


    /**
     * 材料
     */
    private String anyuanYuyueFile;


    /**
     * 案件类型
     */
    private Integer anyuanYuyueTypes;


    /**
     * 结案审批
     */
    private Integer anyuanYuyueYesnoTypes;


    /**
     * 审批回复
     */
    private String anyuanYuyueYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date anyuanYuyueShenheTime;


    /**
     * 收案时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：案源
	 */
    public Integer getAnyuanId() {
        return anyuanId;
    }


    /**
	 * 设置：案源
	 */
    public void setAnyuanId(Integer anyuanId) {
        this.anyuanId = anyuanId;
    }
    /**
	 * 获取：律师
	 */
    public Integer getLvshiId() {
        return lvshiId;
    }


    /**
	 * 设置：律师
	 */
    public void setLvshiId(Integer lvshiId) {
        this.lvshiId = lvshiId;
    }
    /**
	 * 获取：案源分析
	 */
    public String getAnyuanYuyueText() {
        return anyuanYuyueText;
    }


    /**
	 * 设置：案源分析
	 */
    public void setAnyuanYuyueText(String anyuanYuyueText) {
        this.anyuanYuyueText = anyuanYuyueText;
    }
    /**
	 * 获取：流程
	 */
    public String getLiuchengText() {
        return liuchengText;
    }


    /**
	 * 设置：流程
	 */
    public void setLiuchengText(String liuchengText) {
        this.liuchengText = liuchengText;
    }
    /**
	 * 获取：交接记录
	 */
    public String getJiluText() {
        return jiluText;
    }


    /**
	 * 设置：交接记录
	 */
    public void setJiluText(String jiluText) {
        this.jiluText = jiluText;
    }
    /**
	 * 获取：办案经费
	 */
    public Double getJingfeiPrice() {
        return jingfeiPrice;
    }


    /**
	 * 设置：办案经费
	 */
    public void setJingfeiPrice(Double jingfeiPrice) {
        this.jingfeiPrice = jingfeiPrice;
    }
    /**
	 * 获取：材料
	 */
    public String getAnyuanYuyueFile() {
        return anyuanYuyueFile;
    }


    /**
	 * 设置：材料
	 */
    public void setAnyuanYuyueFile(String anyuanYuyueFile) {
        this.anyuanYuyueFile = anyuanYuyueFile;
    }
    /**
	 * 获取：案件类型
	 */
    public Integer getAnyuanYuyueTypes() {
        return anyuanYuyueTypes;
    }


    /**
	 * 设置：案件类型
	 */
    public void setAnyuanYuyueTypes(Integer anyuanYuyueTypes) {
        this.anyuanYuyueTypes = anyuanYuyueTypes;
    }
    /**
	 * 获取：结案审批
	 */
    public Integer getAnyuanYuyueYesnoTypes() {
        return anyuanYuyueYesnoTypes;
    }


    /**
	 * 设置：结案审批
	 */
    public void setAnyuanYuyueYesnoTypes(Integer anyuanYuyueYesnoTypes) {
        this.anyuanYuyueYesnoTypes = anyuanYuyueYesnoTypes;
    }
    /**
	 * 获取：审批回复
	 */
    public String getAnyuanYuyueYesnoText() {
        return anyuanYuyueYesnoText;
    }


    /**
	 * 设置：审批回复
	 */
    public void setAnyuanYuyueYesnoText(String anyuanYuyueYesnoText) {
        this.anyuanYuyueYesnoText = anyuanYuyueYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getAnyuanYuyueShenheTime() {
        return anyuanYuyueShenheTime;
    }


    /**
	 * 设置：审核时间
	 */
    public void setAnyuanYuyueShenheTime(Date anyuanYuyueShenheTime) {
        this.anyuanYuyueShenheTime = anyuanYuyueShenheTime;
    }
    /**
	 * 获取：收案时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：收案时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
