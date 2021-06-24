/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.test.dao;

import com.cny.cnysite.test.entity.TestTree;
import com.cny.cnysite.common.persistence.TreeDao;
import com.cny.cnysite.common.persistence.annotation.MyBatisDao;
import com.cny.cnysite.test.entity.TestTree;

/**
 * 树结构生成DAO接口
 * @author ThinkGem
 * @version 2015-04-06
 */
@MyBatisDao
public interface TestTreeDao extends TreeDao<TestTree> {
	
}