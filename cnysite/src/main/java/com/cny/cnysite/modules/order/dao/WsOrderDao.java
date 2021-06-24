/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.order.dao;

import com.cny.cnysite.common.persistence.CrudDao;
import com.cny.cnysite.common.persistence.annotation.MyBatisDao;
import com.cny.cnysite.modules.order.entity.WsOrder;

/**
 * 订单管理DAO接口
 * @author cny
 * @version 2018-07-24
 */
@MyBatisDao
public interface WsOrderDao extends CrudDao<WsOrder> {
	
}