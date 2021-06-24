/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.prod.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cny.cnysite.common.persistence.Page;
import com.cny.cnysite.common.service.CrudService;
import com.cny.cnysite.modules.prod.entity.WsProdSkuAttr;
import com.cny.cnysite.modules.prod.dao.WsProdSkuAttrDao;

/**
 * 产品属性值表管Service
 * @author cny
 * @version 2018-07-14
 */
@Service
@Transactional(readOnly = true)
public class WsProdSkuAttrService extends CrudService<WsProdSkuAttrDao, WsProdSkuAttr> {

	public WsProdSkuAttr get(String id) {
		return super.get(id);
	}
	
	public List<WsProdSkuAttr> findList(WsProdSkuAttr wsProdSkuAttr) {
		return super.findList(wsProdSkuAttr);
	}
	
	public Page<WsProdSkuAttr> findPage(Page<WsProdSkuAttr> page, WsProdSkuAttr wsProdSkuAttr) {
		return super.findPage(page, wsProdSkuAttr);
	}
	
	@Transactional(readOnly = false)
	public void save(WsProdSkuAttr wsProdSkuAttr) {
		super.save(wsProdSkuAttr);
	}
	
	@Transactional(readOnly = false)
	public void delete(WsProdSkuAttr wsProdSkuAttr) {
		super.delete(wsProdSkuAttr);
	}

	@Transactional(readOnly = false)
	public void deleteByProductId(String productId) {
		dao.deleteByProductId(productId);
	}

	/**
	 * 注意此方法只有insert没有update，和save方法有区别
	 * @param wsProdSkuAttrs
	 */
	@Transactional(readOnly = false)
	public void insertList(List<WsProdSkuAttr> wsProdSkuAttrs) {
		for (WsProdSkuAttr skuAttr:wsProdSkuAttrs){
			//super.save(skuAttr);//不能用此代码，否则skuAttr有可能会有id，只会执行更新操作
			skuAttr.preInsert();
			dao.insert(skuAttr);
		}
	}

}