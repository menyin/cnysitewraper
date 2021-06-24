/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.prod.entity;

import org.hibernate.validator.constraints.Length;

import com.cny.cnysite.common.persistence.DataEntity;

/**
 * 产品sku表管理Entity
 * @author cny
 * @version 2018-07-14
 */
public class WsProdSku extends DataEntity<WsProdSku> {
	
	private static final long serialVersionUID = 1L;
	private WsProduct product;		// 产品编号
	private String skuName;		// sku名称,拼接的销售属性名称以逗号分隔,如：蓝色，250ml
	private String attributeValues;		// 规格属性编号集合，以逗号分隔
	private String attrivalueValues;		// 规格属性值编号集合，以逗号分隔
	private String surplusQuantity;		// 剩余数量
	private String price;		// 价格
	private String reallyPrice;		// 实际价格,这个价格可以根据不同的营销策略做调整并存储
	private String rewardMoney;		// 分销金额
	private String state;		// 状态,0无效11有效
	
	public WsProdSku() {
		super();
	}

	public WsProdSku(String id){
		super(id);
	}

	public WsProduct getProduct() {
		return product;
	}

	public void setProduct(WsProduct product) {
		this.product = product;
	}
	
	@Length(min=0, max=512, message="sku名称,拼接的销售属性名称以逗号分隔,如：蓝色，250ml长度必须介于 0 和 512 之间")
	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
	
	@Length(min=0, max=1000, message="规格属性编号集合，以逗号分隔长度必须介于 0 和 1000 之间")
	public String getAttributeValues() {
		return attributeValues;
	}

	public void setAttributeValues(String attributeValues) {
		this.attributeValues = attributeValues;
	}
	
	@Length(min=0, max=1000, message="规格属性值编号集合，以逗号分隔长度必须介于 0 和 1000 之间")
	public String getAttrivalueValues() {
		return attrivalueValues;
	}

	public void setAttrivalueValues(String attrivalueValues) {
		this.attrivalueValues = attrivalueValues;
	}
	
	@Length(min=0, max=11, message="剩余数量长度必须介于 0 和 11 之间")
	public String getSurplusQuantity() {
		return surplusQuantity;
	}

	public void setSurplusQuantity(String surplusQuantity) {
		this.surplusQuantity = surplusQuantity;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getReallyPrice() {
		return reallyPrice;
	}

	public void setReallyPrice(String reallyPrice) {
		this.reallyPrice = reallyPrice;
	}
	
	public String getRewardMoney() {
		return rewardMoney;
	}

	public void setRewardMoney(String rewardMoney) {
		this.rewardMoney = rewardMoney;
	}
	
	@Length(min=0, max=10, message="状态,0无效11有效长度必须介于 0 和 10 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}