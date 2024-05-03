package com.entity.vo;

import com.entity.AnyuanEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 案源信息
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("anyuan")
public class AnyuanVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 案源编号
     */

    @TableField(value = "anyuan_uuid_number")
    private String anyuanUuidNumber;


    /**
     * 案源名称
     */

    @TableField(value = "anyuan_name")
    private String anyuanName;


    /**
     * 案源类型
     */

    @TableField(value = "anyuan_types")
    private Integer anyuanTypes;


    /**
     * 客户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 案源介绍
     */

    @TableField(value = "anyuan_content")
    private String anyuanContent;


    /**
     * 收案审批
     */

    @TableField(value = "anyuan_yesno_types")
    private Integer anyuanYesnoTypes;


    /**
     * 审批回复
     */

    @TableField(value = "anyuan_yesno_text")
    private String anyuanYesnoText;


    /**
     * 逻辑删除
     */

    @TableField(value = "anyuan_delete")
    private Integer anyuanDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 设置：案源编号
	 */
    public String getAnyuanUuidNumber() {
        return anyuanUuidNumber;
    }


    /**
	 * 获取：案源编号
	 */

    public void setAnyuanUuidNumber(String anyuanUuidNumber) {
        this.anyuanUuidNumber = anyuanUuidNumber;
    }
    /**
	 * 设置：案源名称
	 */
    public String getAnyuanName() {
        return anyuanName;
    }


    /**
	 * 获取：案源名称
	 */

    public void setAnyuanName(String anyuanName) {
        this.anyuanName = anyuanName;
    }
    /**
	 * 设置：案源类型
	 */
    public Integer getAnyuanTypes() {
        return anyuanTypes;
    }


    /**
	 * 获取：案源类型
	 */

    public void setAnyuanTypes(Integer anyuanTypes) {
        this.anyuanTypes = anyuanTypes;
    }
    /**
	 * 设置：客户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：客户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：案源介绍
	 */
    public String getAnyuanContent() {
        return anyuanContent;
    }


    /**
	 * 获取：案源介绍
	 */

    public void setAnyuanContent(String anyuanContent) {
        this.anyuanContent = anyuanContent;
    }
    /**
	 * 设置：收案审批
	 */
    public Integer getAnyuanYesnoTypes() {
        return anyuanYesnoTypes;
    }


    /**
	 * 获取：收案审批
	 */

    public void setAnyuanYesnoTypes(Integer anyuanYesnoTypes) {
        this.anyuanYesnoTypes = anyuanYesnoTypes;
    }
    /**
	 * 设置：审批回复
	 */
    public String getAnyuanYesnoText() {
        return anyuanYesnoText;
    }


    /**
	 * 获取：审批回复
	 */

    public void setAnyuanYesnoText(String anyuanYesnoText) {
        this.anyuanYesnoText = anyuanYesnoText;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getAnyuanDelete() {
        return anyuanDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setAnyuanDelete(Integer anyuanDelete) {
        this.anyuanDelete = anyuanDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
