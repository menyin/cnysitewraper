/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.prod.service;

import java.util.List;

import com.cny.cnysite.modules.prod.dao.WsProdSkuAttrDao;
import com.cny.cnysite.modules.prod.dao.WsProdSkuDao;
import com.cny.cnysite.modules.prod.entity.WsProdSku;
import com.cny.cnysite.modules.prod.entity.WsProdSkuAttr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cny.cnysite.common.persistence.Page;
import com.cny.cnysite.common.service.CrudService;
import com.cny.cnysite.modules.prod.entity.WsProduct;
import com.cny.cnysite.modules.prod.dao.WsProductDao;

/**
 * 产品管理Service
 * @author cny
 * @version 2018-07-13
 */
@Service
@Transactional(readOnly = true)
public class WsProductService extends CrudService<WsProductDao, WsProduct> {
	@Autowired
	private WsProdSkuAttrService wsProdSkuAttrService;
	@Autowired
	private WsProdSkuService wsProdSkuService;

	public WsProduct get(String id) {
		WsProduct wsProduct = super.get(id);

		WsProdSku wsProdSku = new WsProdSku();
		wsProdSku.setProduct(wsProduct);
		List<WsProdSku> prodSkuList = wsProdSkuService.findList(wsProdSku);
		wsProduct.setProdSkuList(prodSkuList);

		WsProdSkuAttr wsProdSkuAttr = new WsProdSkuAttr();
		wsProdSkuAttr.setProd(wsProduct);
		List<WsProdSkuAttr> prodSkuAttrList = wsProdSkuAttrService.findList(wsProdSkuAttr);
		wsProduct.setProdSkuAttrList(prodSkuAttrList);

		return wsProduct;
	}
	
	public List<WsProduct> findList(WsProduct wsProduct) {
		return super.findList(wsProduct);
	}
	
	public Page<WsProduct> findPage(Page<WsProduct> page, WsProduct wsProduct) {
		return super.findPage(page, wsProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(WsProduct wsProduct) {
		List<WsProdSku> prodSkuList = wsProduct.getProdSkuList();
		wsProdSkuService.deleteByProductId(wsProduct.getId());//清除重置商品sku列表
		wsProdSkuService.insertList(prodSkuList);//插入新的sku列表
		List<WsProdSkuAttr> prodSkuAttrList = wsProduct.getProdSkuAttrList();
		wsProdSkuAttrService.deleteByProductId(wsProduct.getId());
		wsProdSkuAttrService.insertList(prodSkuAttrList);
		super.save(wsProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(WsProduct wsProduct) {
		super.delete(wsProduct);
	}
	
}