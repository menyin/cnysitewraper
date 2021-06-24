/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.schoolmanage.dao;

import com.cny.cnysite.common.persistence.TreeDao;
import com.cny.cnysite.common.persistence.annotation.MyBatisDao;
import com.cny.cnysite.modules.schoolmanage.entity.Cadre;

/**
 * 干部管理DAO接口
 * @author 陈南阳
 * @version 2018-05-24
 */
@MyBatisDao
public interface CadreDao extends TreeDao<Cadre> {
	
}