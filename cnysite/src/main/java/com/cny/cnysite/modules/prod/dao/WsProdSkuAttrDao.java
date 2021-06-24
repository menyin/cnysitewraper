/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.prod.dao;

import com.cny.cnysite.common.persistence.CrudDao;
import com.cny.cnysite.common.persistence.annotation.MyBatisDao;
import com.cny.cnysite.modules.prod.entity.WsProdSkuAttr;

/**
 * 产品属性值表管DAO接口
 * @author cny
 * @version 2018-07-14
 */
@MyBatisDao
public interface WsProdSkuAttrDao extends CrudDao<WsProdSkuAttr> {
    public int deleteByProductId(String productId);

}