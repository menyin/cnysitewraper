/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.test.dao;

import com.cny.cnysite.test.entity.TestDataChild;
import com.cny.cnysite.common.persistence.CrudDao;
import com.cny.cnysite.common.persistence.annotation.MyBatisDao;
import com.cny.cnysite.test.entity.TestDataChild;

/**
 * 主子表生成DAO接口
 * @author ThinkGem
 * @version 2015-04-06
 */
@MyBatisDao
public interface TestDataChildDao extends CrudDao<TestDataChild> {
	
}