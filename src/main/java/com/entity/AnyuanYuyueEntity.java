package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 案件信息
 *
 * @author 
 * @email
 */
@TableName("anyuan_yuyue")
public class AnyuanYuyueEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public AnyuanYuyueEntity() {

	}

	public AnyuanYuyueEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 案源
     */
    @ColumnInfo(comment="案源",type="int(11)")
    @TableField(value = "anyuan_id")

    private Integer anyuanId;


    /**
     * 律师
     */
    @ColumnInfo(comment="律师",type="int(11)")
    @TableField(value = "lvshi_id")

    private Integer lvshiId;


    /**
     * 案源分析
     */
    @ColumnInfo(comment="案源分析",type="text")
    @TableField(value = "anyuan_yuyue_text")

    private String anyuanYuyueText;


    /**
     * 流程
     */
    @ColumnInfo(comment="流程",type="text")
    @TableField(value = "liucheng_text")

    private String liuchengText;


    /**
     * 交接记录
     */
    @ColumnInfo(comment="交接记录",type="text")
    @TableField(value = "jilu_text")

    private String jiluText;


    /**
     * 办案经费
     */
    @ColumnInfo(comment="办案经费",type="decimal(10,2)")
    @TableField(value = "jingfei_price")

    private Double jingfeiPrice;


    /**
     * 材料
     */
    @ColumnInfo(comment="材料",type="varchar(200)")
    @TableField(value = "anyuan_yuyue_file")

    private String anyuanYuyueFile;


    /**
     * 案件类型
     */
    @ColumnInfo(comment="案件类型",type="int(11)")
    @TableField(value = "anyuan_yuyue_types")

    private Integer anyuanYuyueTypes;


    /**
     * 结案审批
     */
    @ColumnInfo(comment="结案审批",type="int(11)")
    @TableField(value = "anyuan_yuyue_yesno_types")

    private Integer anyuanYuyueYesnoTypes;


    /**
     * 审批回复
     */
    @ColumnInfo(comment="审批回复",type="text")
    @TableField(value = "anyuan_yuyue_yesno_text")

    private String anyuanYuyueYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="审核时间",type="timestamp")
    @TableField(value = "anyuan_yuyue_shenhe_time")

    private Date anyuanYuyueShenheTime;


    /**
     * 收案时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="收案时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间  listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "AnyuanYuyue{" +
            ", id=" + id +
            ", anyuanId=" + anyuanId +
            ", lvshiId=" + lvshiId +
            ", anyuanYuyueText=" + anyuanYuyueText +
            ", liuchengText=" + liuchengText +
            ", jiluText=" + jiluText +
            ", jingfeiPrice=" + jingfeiPrice +
            ", anyuanYuyueFile=" + anyuanYuyueFile +
            ", anyuanYuyueTypes=" + anyuanYuyueTypes +
            ", anyuanYuyueYesnoTypes=" + anyuanYuyueYesnoTypes +
            ", anyuanYuyueYesnoText=" + anyuanYuyueYesnoText +
            ", anyuanYuyueShenheTime=" + DateUtil.convertString(anyuanYuyueShenheTime,"yyyy-MM-dd") +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
