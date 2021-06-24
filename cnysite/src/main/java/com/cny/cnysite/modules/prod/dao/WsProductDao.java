/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.prod.dao;

import com.cny.cnysite.common.persistence.CrudDao;
import com.cny.cnysite.common.persistence.annotation.MyBatisDao;
import com.cny.cnysite.modules.prod.entity.WsProduct;

/**
 * 产品管理DAO接口
 * @author cny
 * @version 2018-07-13
 */
@MyBatisDao
public interface WsProductDao extends CrudDao<WsProduct> {
	
}