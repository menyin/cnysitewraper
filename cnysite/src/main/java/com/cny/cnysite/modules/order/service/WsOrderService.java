/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cny.cnysite.common.persistence.Page;
import com.cny.cnysite.common.service.CrudService;
import com.cny.cnysite.common.utils.StringUtils;
import com.cny.cnysite.modules.order.entity.WsOrder;
import com.cny.cnysite.modules.order.dao.WsOrderDao;
import com.cny.cnysite.modules.order.entity.WsOrderItem;
import com.cny.cnysite.modules.order.dao.WsOrderItemDao;

/**
 * 订单管理Service
 * @author cny
 * @version 2018-07-24
 */
@Service
@Transactional(readOnly = true)
public class WsOrderService extends CrudService<WsOrderDao, WsOrder> {

	@Autowired
	private WsOrderItemDao wsOrderItemDao;
	
	public WsOrder get(String id) {
		WsOrder wsOrder = super.get(id);
		wsOrder.setWsOrderItemList(wsOrderItemDao.findList(new WsOrderItem(wsOrder)));
		return wsOrder;
	}
	
	public List<WsOrder> findList(WsOrder wsOrder) {
		return super.findList(wsOrder);
	}
	
	public Page<WsOrder> findPage(Page<WsOrder> page, WsOrder wsOrder) {
		return super.findPage(page, wsOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(WsOrder wsOrder) {
		super.save(wsOrder);
		for (WsOrderItem wsOrderItem : wsOrder.getWsOrderItemList()){
			if (wsOrderItem.getId() == null){
				continue;
			}
			if (WsOrderItem.DEL_FLAG_NORMAL.equals(wsOrderItem.getDelFlag())){
				if (StringUtils.isBlank(wsOrderItem.getId())){
					wsOrderItem.setOrderId(wsOrder);
					wsOrderItem.preInsert();
					wsOrderItemDao.insert(wsOrderItem);
				}else{
					wsOrderItem.preUpdate();
					wsOrderItemDao.update(wsOrderItem);
				}
			}else{
				wsOrderItemDao.delete(wsOrderItem);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(WsOrder wsOrder) {
		super.delete(wsOrder);
		wsOrderItemDao.delete(new WsOrderItem(wsOrder));
	}
	
}