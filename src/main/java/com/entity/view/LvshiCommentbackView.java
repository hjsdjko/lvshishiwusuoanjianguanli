package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.LvshiCommentbackEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 律师意见
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("lvshi_commentback")
public class LvshiCommentbackView extends LvshiCommentbackEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表

	//级联表 律师
		/**
		* 律师名称
		*/

		@ColumnInfo(comment="律师名称",type="varchar(200)")
		private String lvshiName;
		/**
		* 头像
		*/

		@ColumnInfo(comment="头像",type="varchar(255)")
		private String lvshiPhoto;
		/**
		* 律师手机号
		*/

		@ColumnInfo(comment="律师手机号",type="varchar(200)")
		private String lvshiPhone;
		/**
		* 律师身份证号
		*/

		@ColumnInfo(comment="律师身份证号",type="varchar(200)")
		private String lvshiIdNumber;
		/**
		* 邮箱
		*/

		@ColumnInfo(comment="邮箱",type="varchar(200)")
		private String lvshiEmail;
		/**
		* 假删
		*/

		@ColumnInfo(comment="假删",type="int(11)")
		private Integer lvshiDelete;
	//级联表 客户
		/**
		* 客户名称
		*/

		@ColumnInfo(comment="客户名称",type="varchar(200)")
		private String yonghuName;
		/**
		* 头像
		*/

		@ColumnInfo(comment="头像",type="varchar(255)")
		private String yonghuPhoto;
		/**
		* 客户手机号
		*/

		@ColumnInfo(comment="客户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 客户身份证号
		*/

		@ColumnInfo(comment="客户身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 邮箱
		*/

		@ColumnInfo(comment="邮箱",type="varchar(200)")
		private String yonghuEmail;
		/**
		* 假删
		*/

		@ColumnInfo(comment="假删",type="int(11)")
		private Integer yonghuDelete;

	//重复字段
			/**
			* 重复字段 的types
			*/
			@ColumnInfo(comment="重复字段 的types",type="Integer")
			private Integer sexTypes;
				@ColumnInfo(comment="重复字段 的值",type="varchar(200)")
				private String sexValue;


	public LvshiCommentbackView() {

	}

	public LvshiCommentbackView(LvshiCommentbackEntity lvshiCommentbackEntity) {
		try {
			BeanUtils.copyProperties(this, lvshiCommentbackEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





	//级联表的get和set 律师

		/**
		* 获取： 律师名称
		*/
		public String getLvshiName() {
			return lvshiName;
		}
		/**
		* 设置： 律师名称
		*/
		public void setLvshiName(String lvshiName) {
			this.lvshiName = lvshiName;
		}

		/**
		* 获取： 头像
		*/
		public String getLvshiPhoto() {
			return lvshiPhoto;
		}
		/**
		* 设置： 头像
		*/
		public void setLvshiPhoto(String lvshiPhoto) {
			this.lvshiPhoto = lvshiPhoto;
		}

		/**
		* 获取： 律师手机号
		*/
		public String getLvshiPhone() {
			return lvshiPhone;
		}
		/**
		* 设置： 律师手机号
		*/
		public void setLvshiPhone(String lvshiPhone) {
			this.lvshiPhone = lvshiPhone;
		}

		/**
		* 获取： 律师身份证号
		*/
		public String getLvshiIdNumber() {
			return lvshiIdNumber;
		}
		/**
		* 设置： 律师身份证号
		*/
		public void setLvshiIdNumber(String lvshiIdNumber) {
			this.lvshiIdNumber = lvshiIdNumber;
		}

		/**
		* 获取： 邮箱
		*/
		public String getLvshiEmail() {
			return lvshiEmail;
		}
		/**
		* 设置： 邮箱
		*/
		public void setLvshiEmail(String lvshiEmail) {
			this.lvshiEmail = lvshiEmail;
		}

		/**
		* 获取： 假删
		*/
		public Integer getLvshiDelete() {
			return lvshiDelete;
		}
		/**
		* 设置： 假删
		*/
		public void setLvshiDelete(Integer lvshiDelete) {
			this.lvshiDelete = lvshiDelete;
		}
	//级联表的get和set 客户

		/**
		* 获取： 客户名称
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 客户名称
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 客户手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 客户手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 客户身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 客户身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}

		/**
		* 获取： 假删
		*/
		public Integer getYonghuDelete() {
			return yonghuDelete;
		}
		/**
		* 设置： 假删
		*/
		public void setYonghuDelete(Integer yonghuDelete) {
			this.yonghuDelete = yonghuDelete;
		}

	//重复字段
			/**
			* 获取： 重复字段 的types
			*/
			public Integer getSexTypes() {
			return sexTypes;
			}
			/**
			* 设置： 重复字段 的types
			*/
			public void setSexTypes(Integer sexTypes) {
			this.sexTypes = sexTypes;
			}
				public String getSexValue() {
				return sexValue;
				}
				public void setSexValue(String sexValue) {
				this.sexValue = sexValue;
				}

	@Override
	public String toString() {
		return "LvshiCommentbackView{" +
			", lvshiName=" + lvshiName +
			", lvshiPhoto=" + lvshiPhoto +
			", lvshiPhone=" + lvshiPhone +
			", lvshiIdNumber=" + lvshiIdNumber +
			", lvshiEmail=" + lvshiEmail +
			", lvshiDelete=" + lvshiDelete +
			", yonghuName=" + yonghuName +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuEmail=" + yonghuEmail +
			", yonghuDelete=" + yonghuDelete +
			"} " + super.toString();
	}
}
