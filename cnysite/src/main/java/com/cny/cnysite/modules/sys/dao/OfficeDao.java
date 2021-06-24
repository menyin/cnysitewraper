/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.sys.dao;

import com.cny.cnysite.modules.sys.entity.Office;
import com.cny.cnysite.common.persistence.TreeDao;
import com.cny.cnysite.common.persistence.annotation.MyBatisDao;
import com.cny.cnysite.modules.sys.entity.Office;

/**
 * 机构DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
	
}
