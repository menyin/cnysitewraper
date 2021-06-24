/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.prod.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Length;

import com.cny.cnysite.common.persistence.TreeEntity;

/**
 * 产品分类管理Entity
 * @author cny
 * @version 2018-07-10
 */
public class WsProdCategory extends TreeEntity<WsProdCategory> {
	
	private static final long serialVersionUID = 1L;
	private WsProdCategory parent;		// 父级编号
	private String parentIds;		// 所有父级编号
	private String name;		// 名称
	private String title;		// 分类描述
	private String imageurl;		// 分类图片地址
//	private String sort;		// 排序

	public WsProdCategory() {
		super();
	}

	public WsProdCategory(String id){
		super(id);
	}

	@JsonBackReference
	public WsProdCategory getParent() {
		return parent;
	}

	public void setParent(WsProdCategory parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=2000, message="所有父级编号长度必须介于 0 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=500, message="分类描述长度必须介于 0 和 500 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=255, message="分类图片地址长度必须介于 0 和 255 之间")
	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	
//	public String getSort() {
//		return sort;
//	}
//	public void setSort(String sort) {
//		this.sort = sort;
//	}

	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}