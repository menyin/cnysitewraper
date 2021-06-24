/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.prod.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.cny.cnysite.common.persistence.DataEntity;

/**
 * 规格属性管理Entity
 * @author cny
 * @version 2018-07-11
 */
public class WsProdAttribute extends DataEntity<WsProdAttribute> {
	
	private static final long serialVersionUID = 1L;
	private WsProdCategory prodCategory;		// 产品类别
	private String attrName;		// 属性名称
	private String attrType;		// 属性类型
	private String inputType;		// 输入类型
	private String isSearch;		// 是否搜索
	private String isRequire;		// 是否必填
	private String state;		// 状态（是否有效）
	private Integer sort;		// 排序
	private List<WsProdAttrivalue> wsProdAttrivalueList = Lists.newArrayList();		// 子表列表
	
	public WsProdAttribute() {
		super();
	}

	public WsProdAttribute(String id){
		super(id);
	}

	public WsProdCategory getProdCategory() {
		return prodCategory;
	}

	public void setProdCategory(WsProdCategory prodCategory) {
		this.prodCategory = prodCategory;
	}
	
	@Length(min=0, max=255, message="属性名称长度必须介于 0 和 255 之间")
	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	
	@Length(min=0, max=10, message="属性类型长度必须介于 0 和 10 之间")
	public String getAttrType() {
		return attrType;
	}

	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}
	
	@Length(min=0, max=10, message="输入类型长度必须介于 0 和 10 之间")
	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}
	
	@Length(min=0, max=10, message="是否搜索长度必须介于 0 和 10 之间")
	public String getIsSearch() {
		return isSearch;
	}

	public void setIsSearch(String isSearch) {
		this.isSearch = isSearch;
	}
	
	@Length(min=0, max=10, message="是否必填长度必须介于 0 和 10 之间")
	public String getIsRequire() {
		return isRequire;
	}

	public void setIsRequire(String isRequire) {
		this.isRequire = isRequire;
	}
	
	@Length(min=0, max=10, message="状态（是否有效）长度必须介于 0 和 10 之间")
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
	
	public List<WsProdAttrivalue> getWsProdAttrivalueList() {
		return wsProdAttrivalueList;
	}

	public void setWsProdAttrivalueList(List<WsProdAttrivalue> wsProdAttrivalueList) {
		this.wsProdAttrivalueList = wsProdAttrivalueList;
	}
}