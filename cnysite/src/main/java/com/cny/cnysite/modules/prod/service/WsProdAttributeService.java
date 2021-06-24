/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.prod.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cny.cnysite.common.persistence.Page;
import com.cny.cnysite.common.service.CrudService;
import com.cny.cnysite.common.utils.StringUtils;
import com.cny.cnysite.modules.prod.entity.WsProdAttribute;
import com.cny.cnysite.modules.prod.dao.WsProdAttributeDao;
import com.cny.cnysite.modules.prod.entity.WsProdAttrivalue;
import com.cny.cnysite.modules.prod.dao.WsProdAttrivalueDao;

/**
 * 规格属性管理Service
 * @author cny
 * @version 2018-07-11
 */
@Service
@Transactional(readOnly = true)
public class WsProdAttributeService extends CrudService<WsProdAttributeDao, WsProdAttribute> {

	@Autowired
	private WsProdAttrivalueDao wsProdAttrivalueDao;
	@Autowired
	private WsProdAttributeDao wsProdAttributeDao;
	
	public WsProdAttribute get(String id) {
		WsProdAttribute wsProdAttribute = super.get(id);
		wsProdAttribute.setWsProdAttrivalueList(wsProdAttrivalueDao.findList(new WsProdAttrivalue(wsProdAttribute)));
		return wsProdAttribute;
	}
	
	public List<WsProdAttribute> findList(WsProdAttribute wsProdAttribute) {
		return super.findList(wsProdAttribute);
	}

	/**
	 * 获取带wsProdAttrivalueList列表的实体
	 * @param wsProdAttribute
	 * @return
	 */
	public List<WsProdAttribute> findListAV(WsProdAttribute wsProdAttribute) {
		List<WsProdAttribute> wsProdAttributeList = super.findList(wsProdAttribute);
		for (WsProdAttribute wsProdAttributeEntity :wsProdAttributeList){
			WsProdAttrivalue wsProdAttrivalue = new WsProdAttrivalue();
			wsProdAttrivalue.setWsProdAttribute(wsProdAttributeEntity);
			List<WsProdAttrivalue> wsProdAttrivalueList = wsProdAttrivalueDao.findList(wsProdAttrivalue);
			wsProdAttributeEntity.setWsProdAttrivalueList(wsProdAttrivalueList);
		}
		return wsProdAttributeList;
	}

	public Page<WsProdAttribute> findPage(Page<WsProdAttribute> page, WsProdAttribute wsProdAttribute) {
		return super.findPage(page, wsProdAttribute);
	}
	
	@Transactional(readOnly = false)
	public void save(WsProdAttribute wsProdAttribute) {
		super.save(wsProdAttribute);
		for (WsProdAttrivalue wsProdAttrivalue : wsProdAttribute.getWsProdAttrivalueList()){
			if (wsProdAttrivalue.getId() == null){
				continue;
			}
			if (WsProdAttrivalue.DEL_FLAG_NORMAL.equals(wsProdAttrivalue.getDelFlag())){
				if (StringUtils.isBlank(wsProdAttrivalue.getId())){
					wsProdAttrivalue.setWsProdAttribute(wsProdAttribute);
					wsProdAttrivalue.preInsert();
					wsProdAttrivalueDao.insert(wsProdAttrivalue);
				}else{
					wsProdAttrivalue.preUpdate();
					wsProdAttrivalueDao.update(wsProdAttrivalue);
				}
			}else{
				wsProdAttrivalueDao.delete(wsProdAttrivalue);
			}
		}
	}


	
	@Transactional(readOnly = false)
	public void delete(WsProdAttribute wsProdAttribute) {
		super.delete(wsProdAttribute);
		wsProdAttrivalueDao.delete(new WsProdAttrivalue(wsProdAttribute));
	}
	
}