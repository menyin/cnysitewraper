/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.prod.entity;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.cny.cnysite.modules.config.entity.WsExFaretemplate;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.cny.cnysite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 产品管理Entity
 * @author cny
 * @version 2018-07-13
 */
public class WsProduct extends DataEntity<WsProduct> {
	
	private static final long serialVersionUID = 1L;
	private WsProdCategory prodCategory;		// 产品分类
	private WsBrand brand;		// 品牌
	private String pname;		// 产品名称
	private String title;		// 标题
	private String isHomeRecommd;		// 是否首页推荐
	private String prodImage;		// 产品主图
	private String prodImages;		// 产品副图
	private BigDecimal rangePrice;		// 价格范围
	private BigDecimal minPrice;		// 最低价格
	private BigDecimal defaultPrice;		// 市场价格
	private BigDecimal defaultReallyPrice;		// 商城价格
	private BigDecimal defaultRewardMoney;		// 分销金额
	private Integer defaultNum;		// 默认库存数量
	private String onGoodState;		// 产品状态
	private Date onGoodTime;		// 上架时间
	private BigDecimal rewardRate;		// 分销比例
	private String pnumber;		// 货号
	private BigDecimal volume;		// 体积
	private BigDecimal weight;		// 重量
	private WsExFaretemplate express;		// 快递模版
	private String isGift;		// 是否赠品
	private String keyword;		// 关键描述
	private String warehouse;		// 仓库发货地
	private String prodContent;		// 产品描述
	private String isReturn;		// 是否支持退货
	private Integer returnDate;		// 退货有效期(天)
	private Integer selNum;		// 已售数量
	private Integer clickNum;		// 浏览次数
	List<WsProdSku> prodSkuList = new ArrayList<WsProdSku>(); //sku列表
	List<WsProdSkuAttr> prodSkuAttrList = new ArrayList<WsProdSkuAttr>();//属性值列表
	List<WsProdSku> prodSkuListNew = new ArrayList<WsProdSku>(); //sku列表 //防止被ModelAttribute影响
	List<WsProdSkuAttr> prodSkuAttrListNew = new ArrayList<WsProdSkuAttr>();//属性值列表 //防止被ModelAttribute影响

	public List<WsProdSku> getProdSkuListNew() {
		return prodSkuListNew;
	}

	public void setProdSkuListNew(List<WsProdSku> prodSkuListNew) {
		this.prodSkuListNew = prodSkuListNew;
	}

	public List<WsProdSkuAttr> getProdSkuAttrListNew() {
		return prodSkuAttrListNew;
	}

	public void setProdSkuAttrListNew(List<WsProdSkuAttr> prodSkuAttrListNew) {
		this.prodSkuAttrListNew = prodSkuAttrListNew;
	}

	public List<WsProdSkuAttr> getProdSkuAttrList() {
		return prodSkuAttrList;
	}

	public void setProdSkuAttrList(List<WsProdSkuAttr> prodSkuAttrList) {
		this.prodSkuAttrList = prodSkuAttrList;
	}


	public List<WsProdSku> getProdSkuList() {
		return prodSkuList;
	}

	public void setProdSkuList(List<WsProdSku> prodSkuList) {
		this.prodSkuList = prodSkuList;
	}

	public WsProduct() {
		super();
	}

	public WsProduct(String id){
		super(id);
	}

	//@NotBlank(message="产品分类不能为空")
	@NotNull(message="产品分类不能为空")
	public WsProdCategory getProdCategory() {
		return prodCategory;
	}

	public void setProdCategory(WsProdCategory prodCategory) {
		this.prodCategory = prodCategory;
	}
	
	public WsBrand getBrand() {
		return brand;
	}

	public void setBrand(WsBrand brand) {
		this.brand = brand;
	}
	
	@Length(min=1, max=128, message="产品名称长度必须介于 1 和 128 之间")
	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}
	
	@Length(min=1, max=512, message="标题长度必须介于 1 和 512 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getIsHomeRecommd() {
		return isHomeRecommd;
	}

	public void setIsHomeRecommd(String isHomeRecommd) {
		this.isHomeRecommd = isHomeRecommd;
	}
	
	@NotBlank(message = "产品主图必填")
	public String getProdImage() {
		return prodImage;
	}

	public void setProdImage(String prodImage) {
		this.prodImage = prodImage;
	}
	
	public String getProdImages() {
		return prodImages;
	}

	public void setProdImages(String prodImages) {
		this.prodImages = prodImages;
	}
	
	public BigDecimal getRangePrice() {
		return rangePrice;
	}
//	@Min(message = "价格必须大于0",value = 0)
	public void setRangePrice(BigDecimal rangePrice) {
		this.rangePrice = rangePrice;
	}
	
	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}
	
	public BigDecimal getDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(BigDecimal defaultPrice) {
		this.defaultPrice = defaultPrice;
	}
	
	public BigDecimal getDefaultReallyPrice() {
		return defaultReallyPrice;
	}

	public void setDefaultReallyPrice(BigDecimal defaultReallyPrice) {
		this.defaultReallyPrice = defaultReallyPrice;
	}
	
	public BigDecimal getDefaultRewardMoney() {
		return defaultRewardMoney;
	}

	public void setDefaultRewardMoney(BigDecimal defaultRewardMoney) {
		this.defaultRewardMoney = defaultRewardMoney;
	}
	
//	@Length(min=0, max=11, message="默认库存数量长度必须介于 0 和 11 之间")
	public Integer getDefaultNum() {
		return defaultNum;
	}

	public void setDefaultNum(Integer defaultNum) {
		this.defaultNum = defaultNum;
	}
	
	@Length(min=1, max=10, message="产品状态长度必须介于 1 和 10 之间")
	public String getOnGoodState() {
		return onGoodState;
	}

	public void setOnGoodState(String onGoodState) {
		this.onGoodState = onGoodState;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Future(message = "上架时间不能选择过去时间")
	public Date getOnGoodTime() {
		return onGoodTime;
	}

	public void setOnGoodTime(Date onGoodTime) {
		this.onGoodTime = onGoodTime;
	}
	
	public BigDecimal getRewardRate() {
		return rewardRate;
	}

	public void setRewardRate(BigDecimal rewardRate) {
		this.rewardRate = rewardRate;
	}
	
	@Length(min=0, max=255, message="货号长度必须介于 0 和 255 之间")
	public String getPnumber() {
		return pnumber;
	}

	public void setPnumber(String pnumber) {
		this.pnumber = pnumber;
	}
	
	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	
	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	
	public WsExFaretemplate getExpress() {
		return express;
	}

	public void setExpress(WsExFaretemplate express) {
		this.express = express;
	}
	
	@Length(min=0, max=64, message="是否赠品长度必须介于 0 和 64 之间")
	public String getIsGift() {
		return isGift;
	}

	public void setIsGift(String isGift) {
		this.isGift = isGift;
	}
	
	@Length(min=0, max=255, message="关键描述长度必须介于 0 和 255 之间")
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Length(min=0, max=64, message="仓库发货地长度必须介于 0 和 64 之间")
	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	
	@Length(min=0, max=5000, message="产品描述长度必须介于 0 和 5000 之间")
	public String getProdContent() {
		return prodContent;
	}

	public void setProdContent(String prodContent) {
		this.prodContent = prodContent;
	}
	
	@Length(min=0, max=10, message="是否支持退货长度必须介于 0 和 10 之间")
	public String getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(String isReturn) {
		this.isReturn = isReturn;
	}
	
//	@Length(min=0, max=11, message="退货有效期(天)长度必须介于 0 和 11 之间")
	public Integer getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Integer returnDate) {
		this.returnDate = returnDate;
	}
	
	public Integer getSelNum() {
		return selNum;
	}

	public void setSelNum(Integer selNum) {
		this.selNum = selNum;
	}
	
	public Integer getClickNum() {
		return clickNum;
	}

	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}
	
}