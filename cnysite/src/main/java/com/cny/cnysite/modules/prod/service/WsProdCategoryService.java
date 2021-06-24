/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.prod.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cny.cnysite.common.service.TreeService;
import com.cny.cnysite.common.utils.StringUtils;
import com.cny.cnysite.modules.prod.entity.WsProdCategory;
import com.cny.cnysite.modules.prod.dao.WsProdCategoryDao;

/**
 * 产品分类管理Service
 * @author cny
 * @version 2018-07-10
 */
@Service
@Transactional(readOnly = true)
public class WsProdCategoryService extends TreeService<WsProdCategoryDao, WsProdCategory> {

	public WsProdCategory get(String id) {
		return super.get(id);
	}
	
	public List<WsProdCategory> findList(WsProdCategory wsProdCategory) {
		if (StringUtils.isNotBlank(wsProdCategory.getParentIds())){
			wsProdCategory.setParentIds(","+wsProdCategory.getParentIds()+",");
		}
		return super.findList(wsProdCategory);
	}
	
	@Transactional(readOnly = false)
	public void save(WsProdCategory wsProdCategory) {
		super.save(wsProdCategory);
	}
	
	@Transactional(readOnly = false)
	public void delete(WsProdCategory wsProdCategory) {
		super.delete(wsProdCategory);
	}
	
}