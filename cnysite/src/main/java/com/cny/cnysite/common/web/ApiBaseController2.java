/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.common.web;

import com.cny.cnysite.common.security.shiro.session.SessionDAO;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 * 控制器支持类
 * @author ThinkGem
 * @version 2013-3-23
 */
public abstract class ApiBaseController2 extends BaseController {
	@Autowired
	private SessionDAO sessionDAO;
	/**
	 * 参数绑定异常
	 */
//	@Override
	@ExceptionHandler({BindException.class, ConstraintViolationException.class, ValidationException.class})
	@ResponseBody
	public String bindException(final Exception exception) {
//		return "error/400";
		String message = "{msg:'参数绑定异常',code:400,status:-2}";
		if (logger.isDebugEnabled()){
			logger.debug("login fail, active session size: {}, message: {}, exception: {}",
					sessionDAO.getActiveSessions(false).size(), message, exception);
		}
		return message;
	}
	/**
	 * 授权登录异常
	 */
//	@Override
	@ExceptionHandler({AuthenticationException.class,UnauthorizedException.class})
	@ResponseBody
	public String authenticationException(final Exception exception) {
//        return "error/403";
		String message = "{msg:'权限不足',code:403,status:-1}";
		if (logger.isDebugEnabled()){
			logger.debug("login fail, active session size: {}, message: {}, exception: {}",
					sessionDAO.getActiveSessions(false).size(), message, exception);
		}
		return message;
	}
	/**
	 * 参数绑定异常
	 */
	@ExceptionHandler({Throwable.class})
	@ResponseBody
	public String throwableException(final Throwable throwable) {
//		return "error/400";
		String message = "{msg:'系统错误',code:500,status:-2}";
		if (logger.isDebugEnabled()){
			logger.debug("login fail, active session size: {}, message: {}, exception: {}",
					sessionDAO.getActiveSessions(false).size(), message, throwable);
		}
		return message;
	}
	
}
