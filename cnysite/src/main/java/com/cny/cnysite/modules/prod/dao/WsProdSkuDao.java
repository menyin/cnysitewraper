/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.prod.dao;

import com.cny.cnysite.common.persistence.CrudDao;
import com.cny.cnysite.common.persistence.annotation.MyBatisDao;
import com.cny.cnysite.modules.prod.entity.WsProdSku;

import java.util.List;

/**
 * 产品sku表管理DAO接口
 * @author cny
 * @version 2018-07-14
 */
@MyBatisDao
public interface WsProdSkuDao extends CrudDao<WsProdSku> {
    public int deleteByProductId(String productId);
    public int insertList(List<WsProdSku> wsProdSkus);
}