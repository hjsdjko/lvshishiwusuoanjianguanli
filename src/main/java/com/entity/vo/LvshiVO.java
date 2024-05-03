package com.entity.vo;

import com.entity.LvshiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 律师
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("lvshi")
public class LvshiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 账户
     */

    @TableField(value = "username")
    private String username;


    /**
     * 密码
     */

    @TableField(value = "password")
    private String password;


    /**
     * 律师名称
     */

    @TableField(value = "lvshi_name")
    private String lvshiName;


    /**
     * 头像
     */

    @TableField(value = "lvshi_photo")
    private String lvshiPhoto;


    /**
     * 律师手机号
     */

    @TableField(value = "lvshi_phone")
    private String lvshiPhone;


    /**
     * 律师身份证号
     */

    @TableField(value = "lvshi_id_number")
    private String lvshiIdNumber;


    /**
     * 性别
     */

    @TableField(value = "sex_types")
    private Integer sexTypes;


    /**
     * 邮箱
     */

    @TableField(value = "lvshi_email")
    private String lvshiEmail;


    /**
     * 假删
     */

    @TableField(value = "lvshi_delete")
    private Integer lvshiDelete;


    /**
     * 创建时间
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
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：律师名称
	 */
    public String getLvshiName() {
        return lvshiName;
    }


    /**
	 * 获取：律师名称
	 */

    public void setLvshiName(String lvshiName) {
        this.lvshiName = lvshiName;
    }
    /**
	 * 设置：头像
	 */
    public String getLvshiPhoto() {
        return lvshiPhoto;
    }


    /**
	 * 获取：头像
	 */

    public void setLvshiPhoto(String lvshiPhoto) {
        this.lvshiPhoto = lvshiPhoto;
    }
    /**
	 * 设置：律师手机号
	 */
    public String getLvshiPhone() {
        return lvshiPhone;
    }


    /**
	 * 获取：律师手机号
	 */

    public void setLvshiPhone(String lvshiPhone) {
        this.lvshiPhone = lvshiPhone;
    }
    /**
	 * 设置：律师身份证号
	 */
    public String getLvshiIdNumber() {
        return lvshiIdNumber;
    }


    /**
	 * 获取：律师身份证号
	 */

    public void setLvshiIdNumber(String lvshiIdNumber) {
        this.lvshiIdNumber = lvshiIdNumber;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：邮箱
	 */
    public String getLvshiEmail() {
        return lvshiEmail;
    }


    /**
	 * 获取：邮箱
	 */

    public void setLvshiEmail(String lvshiEmail) {
        this.lvshiEmail = lvshiEmail;
    }
    /**
	 * 设置：假删
	 */
    public Integer getLvshiDelete() {
        return lvshiDelete;
    }


    /**
	 * 获取：假删
	 */

    public void setLvshiDelete(Integer lvshiDelete) {
        this.lvshiDelete = lvshiDelete;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
