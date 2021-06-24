/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.schoolmanage.dao;

import com.cny.cnysite.common.persistence.CrudDao;
import com.cny.cnysite.common.persistence.annotation.MyBatisDao;
import com.cny.cnysite.modules.schoolmanage.entity.Student;

/**
 * 学生管理DAO接口
 * @author 陈南阳
 * @version 2018-05-25
 */
@MyBatisDao
public interface StudentDao extends CrudDao<Student> {
	
}