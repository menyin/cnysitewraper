/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.config.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cny.cnysite.common.persistence.Page;
import com.cny.cnysite.common.service.CrudService;
import com.cny.cnysite.common.utils.StringUtils;
import com.cny.cnysite.modules.config.entity.WsExFaretemplate;
import com.cny.cnysite.modules.config.dao.WsExFaretemplateDao;
import com.cny.cnysite.modules.config.entity.WsExCarrymode;
import com.cny.cnysite.modules.config.dao.WsExCarrymodeDao;

/**
 * 快递模板管理Service
 * @author cny
 * @version 2018-07-12
 */
@Service
@Transactional(readOnly = true)
public class WsExFaretemplateService extends CrudService<WsExFaretemplateDao, WsExFaretemplate> {

	@Autowired
	private WsExCarrymodeDao wsExCarrymodeDao;
	
	public WsExFaretemplate get(String id) {
		WsExFaretemplate wsExFaretemplate = super.get(id);
		wsExFaretemplate.setWsExCarrymodeList(wsExCarrymodeDao.findList(new WsExCarrymode(wsExFaretemplate)));
		return wsExFaretemplate;
	}
	
	public List<WsExFaretemplate> findList(WsExFaretemplate wsExFaretemplate) {
		return super.findList(wsExFaretemplate);
	}
	
	public Page<WsExFaretemplate> findPage(Page<WsExFaretemplate> page, WsExFaretemplate wsExFaretemplate) {
		return super.findPage(page, wsExFaretemplate);
	}
	
	@Transactional(readOnly = false)
	public void save(WsExFaretemplate wsExFaretemplate) {
		super.save(wsExFaretemplate);
		for (WsExCarrymode wsExCarrymode : wsExFaretemplate.getWsExCarrymodeList()){
			if (wsExCarrymode.getId() == null){
				continue;
			}
			if (WsExCarrymode.DEL_FLAG_NORMAL.equals(wsExCarrymode.getDelFlag())){
				if (StringUtils.isBlank(wsExCarrymode.getId())){
					wsExCarrymode.setFare(wsExFaretemplate);
					wsExCarrymode.preInsert();
					wsExCarrymodeDao.insert(wsExCarrymode);
				}else{
					wsExCarrymode.preUpdate();
					wsExCarrymodeDao.update(wsExCarrymode);
				}
			}else{
				wsExCarrymodeDao.delete(wsExCarrymode);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(WsExFaretemplate wsExFaretemplate) {
		super.delete(wsExFaretemplate);
		wsExCarrymodeDao.delete(new WsExCarrymode(wsExFaretemplate));
	}
	
}