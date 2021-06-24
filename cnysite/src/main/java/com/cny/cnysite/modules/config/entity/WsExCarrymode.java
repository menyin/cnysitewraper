/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.config.entity;

import com.cny.cnysite.modules.sys.entity.Area;
import org.hibernate.validator.constraints.Length;

import com.cny.cnysite.common.persistence.DataEntity;

import java.math.BigDecimal;

/**
 * 快递模板管理Entity
 * @author cny
 * @version 2018-07-12
 */
public class WsExCarrymode extends DataEntity<WsExCarrymode> {
	
	private static final long serialVersionUID = 1L;
	private WsExFaretemplate fare;		// 快递模板 父类
	private Area region;		// 地区
	private BigDecimal firstPiece;		// 首件数量
	private BigDecimal firstWeight;		// 首件重量
	private BigDecimal firstBulk;		// 首体积
	private BigDecimal firstAmount;		// 首费用
	private BigDecimal secondPiece;		// 续件
	private BigDecimal secondWeight;		// 续重量
	private BigDecimal secondBulk;		// 续体积
	private BigDecimal secondAmount;		// 续费
	private String isDefault;		// 是否默认运费
	
	public WsExCarrymode() {
		super();
	}

	public WsExCarrymode(String id){
		super(id);
	}

	public WsExCarrymode(WsExFaretemplate fare){
		this.fare = fare;
	}

	public WsExFaretemplate getFare() {
		return fare;
	}

	public void setFare(WsExFaretemplate fare) {
		this.fare = fare;
	}
	
	public Area getRegion() {
		return region;
	}

	public void setRegion(Area region) {
		this.region = region;
	}
	
	public BigDecimal getFirstPiece() {
		return firstPiece;
	}

	public void setFirstPiece(BigDecimal firstPiece) {
		this.firstPiece = firstPiece;
	}
	
	public BigDecimal getFirstWeight() {
		return firstWeight;
	}

	public void setFirstWeight(BigDecimal firstWeight) {
		this.firstWeight = firstWeight;
	}
	
	public BigDecimal getFirstBulk() {
		return firstBulk;
	}

	public void setFirstBulk(BigDecimal firstBulk) {
		this.firstBulk = firstBulk;
	}
	
	public BigDecimal getFirstAmount() {
		return firstAmount;
	}

	public void setFirstAmount(BigDecimal firstAmount) {
		this.firstAmount = firstAmount;
	}
	
	public BigDecimal getSecondPiece() {
		return secondPiece;
	}

	public void setSecondPiece(BigDecimal secondPiece) {
		this.secondPiece = secondPiece;
	}
	
	public BigDecimal getSecondWeight() {
		return secondWeight;
	}

	public void setSecondWeight(BigDecimal secondWeight) {
		this.secondWeight = secondWeight;
	}
	
	public BigDecimal getSecondBulk() {
		return secondBulk;
	}

	public void setSecondBulk(BigDecimal secondBulk) {
		this.secondBulk = secondBulk;
	}
	
	public BigDecimal getSecondAmount() {
		return secondAmount;
	}

	public void setSecondAmount(BigDecimal secondAmount) {
		this.secondAmount = secondAmount;
	}
	
	@Length(min=0, max=10, message="是否默认运费长度必须介于 0 和 10 之间")
	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	
}