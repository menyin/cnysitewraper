/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.schoolmanage.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cny.cnysite.common.service.TreeService;
import com.cny.cnysite.common.utils.StringUtils;
import com.cny.cnysite.modules.schoolmanage.entity.Cadre;
import com.cny.cnysite.modules.schoolmanage.dao.CadreDao;

/**
 * 干部管理Service
 * @author 陈南阳
 * @version 2018-05-24
 */
@Service
@Transactional(readOnly = true)
public class CadreService extends TreeService<CadreDao, Cadre> {

	public Cadre get(String id) {
		return super.get(id);
	}
	
	public List<Cadre> findList(Cadre cadre) {
		if (StringUtils.isNotBlank(cadre.getParentIds())){
			cadre.setParentIds(","+cadre.getParentIds()+",");
		}
		return super.findList(cadre);
	}
	
	@Transactional(readOnly = false)
	public void save(Cadre cadre) {
		super.save(cadre);
	}
	
	@Transactional(readOnly = false)
	public void delete(Cadre cadre) {
		super.delete(cadre);
	}
	
}