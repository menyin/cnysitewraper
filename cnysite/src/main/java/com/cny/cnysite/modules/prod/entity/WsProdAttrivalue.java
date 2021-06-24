/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.prod.entity;

import org.hibernate.validator.constraints.Length;

import com.cny.cnysite.common.persistence.DataEntity;

/**
 * 规格属性管理Entity
 * @author cny
 * @version 2018-07-11
 */
public class WsProdAttrivalue extends DataEntity<WsProdAttrivalue> {
	
	private static final long serialVersionUID = 1L;
	private WsProdAttribute wsProdAttribute;		// 规格属性 父类
	private String attrvalueValue;		// 规格属性值
	private String state;		// 是否有效
	private Integer sort;		// 排序
	
	public WsProdAttrivalue() {
		super();
	}

	public WsProdAttrivalue(String id){
		super(id);
	}

	public WsProdAttrivalue(WsProdAttribute wsProdAttribute){
		this.wsProdAttribute = wsProdAttribute;
	}

	@Length(min=0, max=64, message="规格属性长度必须介于 0 和 64 之间")
	public WsProdAttribute getWsProdAttribute() {
		return wsProdAttribute;
	}

	public void setWsProdAttribute(WsProdAttribute wsProdAttribute) {
		this.wsProdAttribute = wsProdAttribute;
	}
	
	@Length(min=0, max=255, message="规格属性值长度必须介于 0 和 255 之间")
	public String getAttrvalueValue() {
		return attrvalueValue;
	}

	public void setAttrvalueValue(String attrvalueValue) {
		this.attrvalueValue = attrvalueValue;
	}
	
	@Length(min=0, max=10, message="是否有效长度必须介于 0 和 10 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}