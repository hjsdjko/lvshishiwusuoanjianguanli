package com.entity.vo;

import com.entity.AnyuanYuyueEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 案件信息
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("anyuan_yuyue")
public class AnyuanYuyueVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 案源
     */

    @TableField(value = "anyuan_id")
    private Integer anyuanId;


    /**
     * 律师
     */

    @TableField(value = "lvshi_id")
    private Integer lvshiId;


    /**
     * 案源分析
     */

    @TableField(value = "anyuan_yuyue_text")
    private String anyuanYuyueText;


    /**
     * 流程
     */

    @TableField(value = "liucheng_text")
    private String liuchengText;


    /**
     * 交接记录
     */

    @TableField(value = "jilu_text")
    private String jiluText;


    /**
     * 办案经费
     */

    @TableField(value = "jingfei_price")
    private Double jingfeiPrice;


    /**
     * 材料
     */

    @TableField(value = "anyuan_yuyue_file")
    private String anyuanYuyueFile;


    /**
     * 案件类型
     */

    @TableField(value = "anyuan_yuyue_types")
    private Integer anyuanYuyueTypes;


    /**
     * 结案审批
     */

    @TableField(value = "anyuan_yuyue_yesno_types")
    private Integer anyuanYuyueYesnoTypes;


    /**
     * 审批回复
     */

    @TableField(value = "anyuan_yuyue_yesno_text")
    private String anyuanYuyueYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "anyuan_yuyue_shenhe_time")
    private Date anyuanYuyueShenheTime;


    /**
     * 收案时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：案源
	 */
    public Integer getAnyuanId() {
        return anyuanId;
    }


    /**
	 * 获取：案源
	 */

    public void setAnyuanId(Integer anyuanId) {
        this.anyuanId = anyuanId;
    }
    /**
	 * 设置：律师
	 */
    public Integer getLvshiId() {
        return lvshiId;
    }


    /**
	 * 获取：律师
	 */

    public void setLvshiId(Integer lvshiId) {
        this.lvshiId = lvshiId;
    }
    /**
	 * 设置：案源分析
	 */
    public String getAnyuanYuyueText() {
        return anyuanYuyueText;
    }


    /**
	 * 获取：案源分析
	 */

    public void setAnyuanYuyueText(String anyuanYuyueText) {
        this.anyuanYuyueText = anyuanYuyueText;
    }
    /**
	 * 设置：流程
	 */
    public String getLiuchengText() {
        return liuchengText;
    }


    /**
	 * 获取：流程
	 */

    public void setLiuchengText(String liuchengText) {
        this.liuchengText = liuchengText;
    }
    /**
	 * 设置：交接记录
	 */
    public String getJiluText() {
        return jiluText;
    }


    /**
	 * 获取：交接记录
	 */

    public void setJiluText(String jiluText) {
        this.jiluText = jiluText;
    }
    /**
	 * 设置：办案经费
	 */
    public Double getJingfeiPrice() {
        return jingfeiPrice;
    }


    /**
	 * 获取：办案经费
	 */

    public void setJingfeiPrice(Double jingfeiPrice) {
        this.jingfeiPrice = jingfeiPrice;
    }
    /**
	 * 设置：材料
	 */
    public String getAnyuanYuyueFile() {
        return anyuanYuyueFile;
    }


    /**
	 * 获取：材料
	 */

    public void setAnyuanYuyueFile(String anyuanYuyueFile) {
        this.anyuanYuyueFile = anyuanYuyueFile;
    }
    /**
	 * 设置：案件类型
	 */
    public Integer getAnyuanYuyueTypes() {
        return anyuanYuyueTypes;
    }


    /**
	 * 获取：案件类型
	 */

    public void setAnyuanYuyueTypes(Integer anyuanYuyueTypes) {
        this.anyuanYuyueTypes = anyuanYuyueTypes;
    }
    /**
	 * 设置：结案审批
	 */
    public Integer getAnyuanYuyueYesnoTypes() {
        return anyuanYuyueYesnoTypes;
    }


    /**
	 * 获取：结案审批
	 */

    public void setAnyuanYuyueYesnoTypes(Integer anyuanYuyueYesnoTypes) {
        this.anyuanYuyueYesnoTypes = anyuanYuyueYesnoTypes;
    }
    /**
	 * 设置：审批回复
	 */
    public String getAnyuanYuyueYesnoText() {
        return anyuanYuyueYesnoText;
    }


    /**
	 * 获取：审批回复
	 */

    public void setAnyuanYuyueYesnoText(String anyuanYuyueYesnoText) {
        this.anyuanYuyueYesnoText = anyuanYuyueYesnoText;
    }
    /**
	 * 设置：审核时间
	 */
    public Date getAnyuanYuyueShenheTime() {
        return anyuanYuyueShenheTime;
    }


    /**
	 * 获取：审核时间
	 */

    public void setAnyuanYuyueShenheTime(Date anyuanYuyueShenheTime) {
        this.anyuanYuyueShenheTime = anyuanYuyueShenheTime;
    }
    /**
	 * 设置：收案时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：收案时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
