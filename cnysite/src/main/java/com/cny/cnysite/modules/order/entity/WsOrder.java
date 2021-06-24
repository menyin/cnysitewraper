/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.order.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.google.common.collect.Lists;

import com.cny.cnysite.common.persistence.DataEntity;

/**
 * 订单管理Entity
 * @author cny
 * @version 2018-07-24
 */
public class WsOrder extends DataEntity<WsOrder> {
	
	private static final long serialVersionUID = 1L;
	private String orderSn;		// 订单编号
	private String orderState;		// 订单状态
	private String jsPayState;		// 支付成功
	private String buysWords;		// 买家留言
	private String memberId;		// 会员
	private String ruid;		// 推荐人会员
	private String logisticsMethod;		// 物流方式
	private Date paytime;		// 付款时间
	private Date sendTime;		// 发货时间
	private Date receviceTime;		// 收货时间
	private String payment;		// 支付方式
	private String trackingno;		// 快递单号
	private String express;		// 快递公司
	private String postage;		// 运费
	private String couponId;		// 优惠券编号
	private String couponMoney;		// 优惠券优惠金额
	private String price;		// 订单总价
	private String reallyPrice;		// 实际支付金额
	private String score;		// 赠送积分
	private String addressId;		// 收货地址编号
	private String consignee;		// 收货人名称
	private String tel;		// 电话
	private String zipCode;		// 邮编
	private String city;		// 城市
	private String address;		// 详细地址
	private String prepayId;		// 预支付编号，支付方式不同，长度不同。其中微信支付有效期为2小时
	private Date prepayDate;		// 预支付编号生成的时间
	private String returnState;		// 退款状态
	private String returnId;		// 退款编号
	private String returnMoney;		// 退款金额
	private List<WsOrderItem> wsOrderItemList = Lists.newArrayList();		// 子表列表
	
	public WsOrder() {
		super();
	}

	public WsOrder(String id){
		super(id);
	}

	@Length(min=0, max=255, message="订单编号长度必须介于 0 和 255 之间")
	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	
	@Length(min=0, max=10, message="订单状态长度必须介于 0 和 10 之间")
	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	
	@Length(min=0, max=10, message="支付成功长度必须介于 0 和 10 之间")
	public String getJsPayState() {
		return jsPayState;
	}

	public void setJsPayState(String jsPayState) {
		this.jsPayState = jsPayState;
	}
	
	@Length(min=0, max=255, message="买家留言长度必须介于 0 和 255 之间")
	public String getBuysWords() {
		return buysWords;
	}

	public void setBuysWords(String buysWords) {
		this.buysWords = buysWords;
	}
	
	@Length(min=0, max=64, message="会员长度必须介于 0 和 64 之间")
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	public String getRuid() {
		return ruid;
	}

	public void setRuid(String ruid) {
		this.ruid = ruid;
	}
	
	@Length(min=0, max=10, message="物流方式长度必须介于 0 和 10 之间")
	public String getLogisticsMethod() {
		return logisticsMethod;
	}

	public void setLogisticsMethod(String logisticsMethod) {
		this.logisticsMethod = logisticsMethod;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPaytime() {
		return paytime;
	}

	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReceviceTime() {
		return receviceTime;
	}

	public void setReceviceTime(Date receviceTime) {
		this.receviceTime = receviceTime;
	}
	
	@Length(min=0, max=10, message="支付方式长度必须介于 0 和 10 之间")
	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}
	
	@Length(min=0, max=255, message="快递单号长度必须介于 0 和 255 之间")
	public String getTrackingno() {
		return trackingno;
	}

	public void setTrackingno(String trackingno) {
		this.trackingno = trackingno;
	}
	
	@Length(min=0, max=255, message="快递公司长度必须介于 0 和 255 之间")
	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}
	
	public String getPostage() {
		return postage;
	}

	public void setPostage(String postage) {
		this.postage = postage;
	}
	
	@Length(min=0, max=64, message="优惠券编号长度必须介于 0 和 64 之间")
	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	
	public String getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(String couponMoney) {
		this.couponMoney = couponMoney;
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
	
	@Length(min=0, max=11, message="赠送积分长度必须介于 0 和 11 之间")
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	@Length(min=0, max=64, message="收货地址编号长度必须介于 0 和 64 之间")
	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	
	@Length(min=0, max=50, message="收货人名称长度必须介于 0 和 50 之间")
	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	
	@Length(min=0, max=30, message="电话长度必须介于 0 和 30 之间")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Length(min=0, max=30, message="邮编长度必须介于 0 和 30 之间")
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	@Length(min=0, max=100, message="城市长度必须介于 0 和 100 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=1000, message="详细地址长度必须介于 0 和 1000 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=100, message="预支付编号，支付方式不同，长度不同。其中微信支付有效期为2小时长度必须介于 0 和 100 之间")
	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPrepayDate() {
		return prepayDate;
	}

	public void setPrepayDate(Date prepayDate) {
		this.prepayDate = prepayDate;
	}
	
	@Length(min=0, max=10, message="退款状态长度必须介于 0 和 10 之间")
	public String getReturnState() {
		return returnState;
	}

	public void setReturnState(String returnState) {
		this.returnState = returnState;
	}
	
	@Length(min=0, max=64, message="退款编号长度必须介于 0 和 64 之间")
	public String getReturnId() {
		return returnId;
	}

	public void setReturnId(String returnId) {
		this.returnId = returnId;
	}
	
	public String getReturnMoney() {
		return returnMoney;
	}

	public void setReturnMoney(String returnMoney) {
		this.returnMoney = returnMoney;
	}
	
	public List<WsOrderItem> getWsOrderItemList() {
		return wsOrderItemList;
	}

	public void setWsOrderItemList(List<WsOrderItem> wsOrderItemList) {
		this.wsOrderItemList = wsOrderItemList;
	}
}