/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.prod.dao;

import com.cny.cnysite.common.persistence.CrudDao;
import com.cny.cnysite.common.persistence.annotation.MyBatisDao;
import com.cny.cnysite.modules.prod.entity.WsBrand;

/**
 * 品牌管理DAO接口
 * @author cny
 * @version 2018-07-11
 */
@MyBatisDao
public interface WsBrandDao extends CrudDao<WsBrand> {
	
}