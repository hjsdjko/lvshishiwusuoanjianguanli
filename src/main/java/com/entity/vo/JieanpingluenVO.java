package com.entity.vo;

import com.entity.JieanpingluenEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 结案评论
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("jieanpingluen")
public class JieanpingluenVO implements Serializable {
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
     * 评论内容
     */

    @TableField(value = "jieanpingluen_text")
    private String jieanpingluenText;


    /**
     * 评论时间
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
	 * 设置：评论内容
	 */
    public String getJieanpingluenText() {
        return jieanpingluenText;
    }


    /**
	 * 获取：评论内容
	 */

    public void setJieanpingluenText(String jieanpingluenText) {
        this.jieanpingluenText = jieanpingluenText;
    }
    /**
	 * 设置：评论时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：评论时间
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
