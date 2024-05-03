package com.entity.model;

import com.entity.LvshiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 律师
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class LvshiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 律师名称
     */
    private String lvshiName;


    /**
     * 头像
     */
    private String lvshiPhoto;


    /**
     * 律师手机号
     */
    private String lvshiPhone;


    /**
     * 律师身份证号
     */
    private String lvshiIdNumber;


    /**
     * 性别
     */
    private Integer sexTypes;


    /**
     * 邮箱
     */
    private String lvshiEmail;


    /**
     * 假删
     */
    private Integer lvshiDelete;


    /**
     * 创建时间
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
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 设置：账户
	 */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 设置：密码
	 */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：律师名称
	 */
    public String getLvshiName() {
        return lvshiName;
    }


    /**
	 * 设置：律师名称
	 */
    public void setLvshiName(String lvshiName) {
        this.lvshiName = lvshiName;
    }
    /**
	 * 获取：头像
	 */
    public String getLvshiPhoto() {
        return lvshiPhoto;
    }


    /**
	 * 设置：头像
	 */
    public void setLvshiPhoto(String lvshiPhoto) {
        this.lvshiPhoto = lvshiPhoto;
    }
    /**
	 * 获取：律师手机号
	 */
    public String getLvshiPhone() {
        return lvshiPhone;
    }


    /**
	 * 设置：律师手机号
	 */
    public void setLvshiPhone(String lvshiPhone) {
        this.lvshiPhone = lvshiPhone;
    }
    /**
	 * 获取：律师身份证号
	 */
    public String getLvshiIdNumber() {
        return lvshiIdNumber;
    }


    /**
	 * 设置：律师身份证号
	 */
    public void setLvshiIdNumber(String lvshiIdNumber) {
        this.lvshiIdNumber = lvshiIdNumber;
    }
    /**
	 * 获取：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 设置：性别
	 */
    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：邮箱
	 */
    public String getLvshiEmail() {
        return lvshiEmail;
    }


    /**
	 * 设置：邮箱
	 */
    public void setLvshiEmail(String lvshiEmail) {
        this.lvshiEmail = lvshiEmail;
    }
    /**
	 * 获取：假删
	 */
    public Integer getLvshiDelete() {
        return lvshiDelete;
    }


    /**
	 * 设置：假删
	 */
    public void setLvshiDelete(Integer lvshiDelete) {
        this.lvshiDelete = lvshiDelete;
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

    }
