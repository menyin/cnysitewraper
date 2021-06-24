/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.config.dao;

import com.cny.cnysite.common.persistence.CrudDao;
import com.cny.cnysite.common.persistence.annotation.MyBatisDao;
import com.cny.cnysite.modules.config.entity.WsExCarrymode;

/**
 * 快递模板管理DAO接口
 * @author cny
 * @version 2018-07-12
 */
@MyBatisDao
public interface WsExCarrymodeDao extends CrudDao<WsExCarrymode> {
	
}