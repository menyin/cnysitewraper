/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.order.entity;

import org.hibernate.validator.constraints.Length;

import com.cny.cnysite.common.persistence.DataEntity;

/**
 * 订单管理Entity
 * @author cny
 * @version 2018-07-24
 */
public class WsOrderItem extends DataEntity<WsOrderItem> {
	
	private static final long serialVersionUID = 1L;
	private WsOrder orderId;		// 订单主表编号 父类
	private String skuId;		// 商品sku编号
	private String quantity;		// 数量
	private String unitPrice;		// 商品单价
	private String reallyUnitPrice;		// 商品实际单价
	private String reallyPrice;		// 实际支付总金额
	private String rewardMoney;		// 分销金额
	
	public WsOrderItem() {
		super();
	}

	public WsOrderItem(String id){
		super(id);
	}

	public WsOrderItem(WsOrder orderId){
		this.orderId = orderId;
	}

	@Length(min=0, max=64, message="订单主表编号长度必须介于 0 和 64 之间")
	public WsOrder getOrderId() {
		return orderId;
	}

	public void setOrderId(WsOrder orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=64, message="商品sku编号长度必须介于 0 和 64 之间")
	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	
	@Length(min=0, max=11, message="数量长度必须介于 0 和 11 之间")
	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public String getReallyUnitPrice() {
		return reallyUnitPrice;
	}

	public void setReallyUnitPrice(String reallyUnitPrice) {
		this.reallyUnitPrice = reallyUnitPrice;
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
	
}