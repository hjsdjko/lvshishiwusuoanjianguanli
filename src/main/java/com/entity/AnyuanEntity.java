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
 * 案源信息
 *
 * @author 
 * @email
 */
@TableName("anyuan")
public class AnyuanEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public AnyuanEntity() {

	}

	public AnyuanEntity(T t) {
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
     * 案源编号
     */
    @ColumnInfo(comment="案源编号",type="varchar(200)")
    @TableField(value = "anyuan_uuid_number")

    private String anyuanUuidNumber;


    /**
     * 案源名称
     */
    @ColumnInfo(comment="案源名称",type="varchar(200)")
    @TableField(value = "anyuan_name")

    private String anyuanName;


    /**
     * 案源类型
     */
    @ColumnInfo(comment="案源类型",type="int(11)")
    @TableField(value = "anyuan_types")

    private Integer anyuanTypes;


    /**
     * 客户
     */
    @ColumnInfo(comment="客户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 案源介绍
     */
    @ColumnInfo(comment="案源介绍",type="text")
    @TableField(value = "anyuan_content")

    private String anyuanContent;


    /**
     * 收案审批
     */
    @ColumnInfo(comment="收案审批",type="int(11)")
    @TableField(value = "anyuan_yesno_types")

    private Integer anyuanYesnoTypes;


    /**
     * 审批回复
     */
    @ColumnInfo(comment="审批回复",type="text")
    @TableField(value = "anyuan_yesno_text")

    private String anyuanYesnoText;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "anyuan_delete")

    private Integer anyuanDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
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
	 * 获取：案源编号
	 */
    public String getAnyuanUuidNumber() {
        return anyuanUuidNumber;
    }
    /**
	 * 设置：案源编号
	 */

    public void setAnyuanUuidNumber(String anyuanUuidNumber) {
        this.anyuanUuidNumber = anyuanUuidNumber;
    }
    /**
	 * 获取：案源名称
	 */
    public String getAnyuanName() {
        return anyuanName;
    }
    /**
	 * 设置：案源名称
	 */

    public void setAnyuanName(String anyuanName) {
        this.anyuanName = anyuanName;
    }
    /**
	 * 获取：案源类型
	 */
    public Integer getAnyuanTypes() {
        return anyuanTypes;
    }
    /**
	 * 设置：案源类型
	 */

    public void setAnyuanTypes(Integer anyuanTypes) {
        this.anyuanTypes = anyuanTypes;
    }
    /**
	 * 获取：客户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 设置：客户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：案源介绍
	 */
    public String getAnyuanContent() {
        return anyuanContent;
    }
    /**
	 * 设置：案源介绍
	 */

    public void setAnyuanContent(String anyuanContent) {
        this.anyuanContent = anyuanContent;
    }
    /**
	 * 获取：收案审批
	 */
    public Integer getAnyuanYesnoTypes() {
        return anyuanYesnoTypes;
    }
    /**
	 * 设置：收案审批
	 */

    public void setAnyuanYesnoTypes(Integer anyuanYesnoTypes) {
        this.anyuanYesnoTypes = anyuanYesnoTypes;
    }
    /**
	 * 获取：审批回复
	 */
    public String getAnyuanYesnoText() {
        return anyuanYesnoText;
    }
    /**
	 * 设置：审批回复
	 */

    public void setAnyuanYesnoText(String anyuanYesnoText) {
        this.anyuanYesnoText = anyuanYesnoText;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getAnyuanDelete() {
        return anyuanDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setAnyuanDelete(Integer anyuanDelete) {
        this.anyuanDelete = anyuanDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Anyuan{" +
            ", id=" + id +
            ", anyuanUuidNumber=" + anyuanUuidNumber +
            ", anyuanName=" + anyuanName +
            ", anyuanTypes=" + anyuanTypes +
            ", yonghuId=" + yonghuId +
            ", anyuanContent=" + anyuanContent +
            ", anyuanYesnoTypes=" + anyuanYesnoTypes +
            ", anyuanYesnoText=" + anyuanYesnoText +
            ", anyuanDelete=" + anyuanDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
