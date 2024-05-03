package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.JieanpingluenEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 结案评论
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("jieanpingluen")
public class JieanpingluenView extends JieanpingluenEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表

	//级联表 案源信息
		/**
		* 案源编号
		*/

		@ColumnInfo(comment="案源编号",type="varchar(200)")
		private String anyuanUuidNumber;
		/**
		* 案源名称
		*/

		@ColumnInfo(comment="案源名称",type="varchar(200)")
		private String anyuanName;
		/**
		* 案源类型
		*/
		@ColumnInfo(comment="案源类型",type="int(11)")
		private Integer anyuanTypes;
			/**
			* 案源类型的值
			*/
			@ColumnInfo(comment="案源类型的字典表值",type="varchar(200)")
			private String anyuanValue;
					 
		/**
		* 案源信息 的 客户
		*/
		@ColumnInfo(comment="客户",type="int(11)")
		private Integer anyuanYonghuId;
		/**
		* 案源介绍
		*/

		@ColumnInfo(comment="案源介绍",type="text")
		private String anyuanContent;
		/**
		* 收案审批
		*/
		@ColumnInfo(comment="收案审批",type="int(11)")
		private Integer anyuanYesnoTypes;
			/**
			* 收案审批的值
			*/
			@ColumnInfo(comment="收案审批的字典表值",type="varchar(200)")
			private String anyuanYesnoValue;
		/**
		* 审批回复
		*/

		@ColumnInfo(comment="审批回复",type="text")
		private String anyuanYesnoText;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer anyuanDelete;



	public JieanpingluenView() {

	}

	public JieanpingluenView(JieanpingluenEntity jieanpingluenEntity) {
		try {
			BeanUtils.copyProperties(this, jieanpingluenEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





	//级联表的get和set 案源信息

		/**
		* 获取： 案源编号
		*/
		public String getAnyuanUuidNumber() {
			return anyuanUuidNumber;
		}
		/**
		* 设置： 案源编号
		*/
		public void setAnyuanUuidNumber(String anyuanUuidNumber) {
			this.anyuanUuidNumber = anyuanUuidNumber;
		}

		/**
		* 获取： 案源名称
		*/
		public String getAnyuanName() {
			return anyuanName;
		}
		/**
		* 设置： 案源名称
		*/
		public void setAnyuanName(String anyuanName) {
			this.anyuanName = anyuanName;
		}
		/**
		* 获取： 案源类型
		*/
		public Integer getAnyuanTypes() {
			return anyuanTypes;
		}
		/**
		* 设置： 案源类型
		*/
		public void setAnyuanTypes(Integer anyuanTypes) {
			this.anyuanTypes = anyuanTypes;
		}


			/**
			* 获取： 案源类型的值
			*/
			public String getAnyuanValue() {
				return anyuanValue;
			}
			/**
			* 设置： 案源类型的值
			*/
			public void setAnyuanValue(String anyuanValue) {
				this.anyuanValue = anyuanValue;
			}
		/**
		* 获取：案源信息 的 客户
		*/
		public Integer getAnyuanYonghuId() {
			return anyuanYonghuId;
		}
		/**
		* 设置：案源信息 的 客户
		*/
		public void setAnyuanYonghuId(Integer anyuanYonghuId) {
			this.anyuanYonghuId = anyuanYonghuId;
		}

		/**
		* 获取： 案源介绍
		*/
		public String getAnyuanContent() {
			return anyuanContent;
		}
		/**
		* 设置： 案源介绍
		*/
		public void setAnyuanContent(String anyuanContent) {
			this.anyuanContent = anyuanContent;
		}
		/**
		* 获取： 收案审批
		*/
		public Integer getAnyuanYesnoTypes() {
			return anyuanYesnoTypes;
		}
		/**
		* 设置： 收案审批
		*/
		public void setAnyuanYesnoTypes(Integer anyuanYesnoTypes) {
			this.anyuanYesnoTypes = anyuanYesnoTypes;
		}


			/**
			* 获取： 收案审批的值
			*/
			public String getAnyuanYesnoValue() {
				return anyuanYesnoValue;
			}
			/**
			* 设置： 收案审批的值
			*/
			public void setAnyuanYesnoValue(String anyuanYesnoValue) {
				this.anyuanYesnoValue = anyuanYesnoValue;
			}

		/**
		* 获取： 审批回复
		*/
		public String getAnyuanYesnoText() {
			return anyuanYesnoText;
		}
		/**
		* 设置： 审批回复
		*/
		public void setAnyuanYesnoText(String anyuanYesnoText) {
			this.anyuanYesnoText = anyuanYesnoText;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getAnyuanDelete() {
			return anyuanDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setAnyuanDelete(Integer anyuanDelete) {
			this.anyuanDelete = anyuanDelete;
		}


	@Override
	public String toString() {
		return "JieanpingluenView{" +
			", anyuanUuidNumber=" + anyuanUuidNumber +
			", anyuanName=" + anyuanName +
			", anyuanContent=" + anyuanContent +
			", anyuanYesnoText=" + anyuanYesnoText +
			", anyuanDelete=" + anyuanDelete +
			"} " + super.toString();
	}
}
