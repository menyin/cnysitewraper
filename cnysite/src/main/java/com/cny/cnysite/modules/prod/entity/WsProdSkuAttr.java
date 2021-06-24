/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.prod.entity;

import org.hibernate.validator.constraints.Length;

import com.cny.cnysite.common.persistence.DataEntity;

/**
 * 产品属性值表管Entity
 * @author cny
 * @version 2018-07-14
 */
public class WsProdSkuAttr extends DataEntity<WsProdSkuAttr> {
	
	private static final long serialVersionUID = 1L;
	private WsProduct prod;		// 产品编号
	private String attrbuteId;		// 规格属性编号
	private String attrbuteName;		// 规格属性名称
	private String attrbuteValue;		// 规格属性值编号
	private String attrbuteValueName;		// 规格属性值名称
	
	public WsProdSkuAttr() {
		super();
	}

	public WsProdSkuAttr(String id){
		super(id);
	}

	public WsProduct getProd() {
		return prod;
	}

	public void setProd(WsProduct prod) {
		this.prod = prod;
	}
	
	@Length(min=0, max=64, message="规格属性编号长度必须介于 0 和 64 之间")
	public String getAttrbuteId() {
		return attrbuteId;
	}

	public void setAttrbuteId(String attrbuteId) {
		this.attrbuteId = attrbuteId;
	}
	
	@Length(min=0, max=100, message="规格属性名称长度必须介于 0 和 100 之间")
	public String getAttrbuteName() {
		return attrbuteName;
	}

	public void setAttrbuteName(String attrbuteName) {
		this.attrbuteName = attrbuteName;
	}
	
	@Length(min=0, max=64, message="规格属性值编号长度必须介于 0 和 64 之间")
	public String getAttrbuteValue() {
		return attrbuteValue;
	}

	public void setAttrbuteValue(String attrbuteValue) {
		this.attrbuteValue = attrbuteValue;
	}
	
	@Length(min=0, max=100, message="规格属性值名称长度必须介于 0 和 100 之间")
	public String getAttrbuteValueName() {
		return attrbuteValueName;
	}

	public void setAttrbuteValueName(String attrbuteValueName) {
		this.attrbuteValueName = attrbuteValueName;
	}
	
}