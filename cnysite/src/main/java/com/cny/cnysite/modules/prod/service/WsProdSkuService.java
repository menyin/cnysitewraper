/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.prod.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cny.cnysite.common.persistence.Page;
import com.cny.cnysite.common.service.CrudService;
import com.cny.cnysite.modules.prod.entity.WsProdSku;
import com.cny.cnysite.modules.prod.dao.WsProdSkuDao;

/**
 * 产品sku表管理Service
 * @author cny
 * @version 2018-07-14
 */
@Service
@Transactional(readOnly = true)
public class WsProdSkuService extends CrudService<WsProdSkuDao, WsProdSku> {

	public WsProdSku get(String id) {
		return super.get(id);
	}
	
	public List<WsProdSku> findList(WsProdSku wsProdSku) {
		return super.findList(wsProdSku);
	}
	
	public Page<WsProdSku> findPage(Page<WsProdSku> page, WsProdSku wsProdSku) {
		return super.findPage(page, wsProdSku);
	}
	
	@Transactional(readOnly = false)
	public void save(WsProdSku wsProdSku) {
		super.save(wsProdSku);
	}

	/**
	 * 注意此方法只有insert没有update，和save方法有区别
	 * @param wsProdSkus
	 */
	@Transactional(readOnly = false)
	public void insertList(List<WsProdSku> wsProdSkus){
		for (WsProdSku sku:wsProdSkus){
			//super.save(sku);//不能用此代码，否则sku有可能会有id，只会执行更新操作
			sku.preInsert();
			dao.insert(sku);
		}
	}


	@Transactional(readOnly = false)
	public void delete(WsProdSku wsProdSku) {
		super.delete(wsProdSku);
	}

	@Transactional(readOnly = false)
	public void deleteByProductId(String productId) {
		dao.deleteByProductId(productId);
	}

}