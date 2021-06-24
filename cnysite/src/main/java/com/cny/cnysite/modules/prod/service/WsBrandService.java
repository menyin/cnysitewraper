/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.prod.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cny.cnysite.common.persistence.Page;
import com.cny.cnysite.common.service.CrudService;
import com.cny.cnysite.modules.prod.entity.WsBrand;
import com.cny.cnysite.modules.prod.dao.WsBrandDao;

/**
 * 品牌管理Service
 * @author cny
 * @version 2018-07-11
 */
@Service
@Transactional(readOnly = true)
public class WsBrandService extends CrudService<WsBrandDao, WsBrand> {

	public WsBrand get(String id) {
		return super.get(id);
	}
	
	public List<WsBrand> findList(WsBrand wsBrand) {
		return super.findList(wsBrand);
	}
	
	public Page<WsBrand> findPage(Page<WsBrand> page, WsBrand wsBrand) {
		return super.findPage(page, wsBrand);
	}
	
	@Transactional(readOnly = false)
	public void save(WsBrand wsBrand) {
		super.save(wsBrand);
	}
	
	@Transactional(readOnly = false)
	public void delete(WsBrand wsBrand) {
		super.delete(wsBrand);
	}
	
}