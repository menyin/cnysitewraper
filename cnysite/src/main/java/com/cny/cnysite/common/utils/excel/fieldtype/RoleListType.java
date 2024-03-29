/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.common.utils.excel.fieldtype;

import java.util.List;

import com.cny.cnysite.common.utils.Collections3;
import com.cny.cnysite.common.utils.SpringContextHolder;
import com.cny.cnysite.common.utils.StringUtils;
import com.cny.cnysite.modules.sys.entity.Role;
import com.cny.cnysite.modules.sys.service.SystemService;
import com.google.common.collect.Lists;
import com.cny.cnysite.common.utils.StringUtils;
import com.cny.cnysite.common.utils.Collections3;
import com.cny.cnysite.common.utils.SpringContextHolder;
import com.cny.cnysite.modules.sys.entity.Role;
import com.cny.cnysite.modules.sys.service.SystemService;

/**
 * 字段类型转换
 * @author ThinkGem
 * @version 2013-5-29
 */
public class RoleListType {

	private static SystemService systemService = SpringContextHolder.getBean(SystemService.class);
	
	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue(String val) {
		List<Role> roleList = Lists.newArrayList();
		List<Role> allRoleList = systemService.findAllRole();
		for (String s : StringUtils.split(val, ",")){
			for (Role e : allRoleList){
				if (StringUtils.trimToEmpty(s).equals(e.getName())){
					roleList.add(e);
				}
			}
		}
		return roleList.size()>0?roleList:null;
	}

	/**
	 * 设置对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null){
			@SuppressWarnings("unchecked")
			List<Role> roleList = (List<Role>)val;
			return Collections3.extractToString(roleList, "name", ", ");
		}
		return "";
	}
	
}
