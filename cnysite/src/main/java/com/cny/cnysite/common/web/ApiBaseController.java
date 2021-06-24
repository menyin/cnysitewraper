/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.common.web;

import com.cny.cnysite.common.beanvalidator.BeanValidators;
import com.cny.cnysite.common.mapper.JsonMapper;
import com.cny.cnysite.common.security.shiro.session.SessionDAO;
import com.cny.cnysite.common.utils.DateUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 控制器支持类
 * @author ThinkGem
 * @version 2013-3-23
 */
public abstract class ApiBaseController extends BaseController {
	@Autowired
	private SessionDAO sessionDAO;
	/**
	 * 参数绑定异常
	 */
	@Override
	@ExceptionHandler({BindException.class, ConstraintViolationException.class, ValidationException.class})
	@ResponseBody
	public String bindException(Exception exception) {
//		return "error/400";
		String message = "{msg:'参数绑定异常',code:400,status:-2}";
		if (logger.isDebugEnabled()){
			logger.debug("parameter binding exception, active session size: {}, message: {}, exception: {}",
					sessionDAO.getActiveSessions(false).size(), message, exception);
		}
		return message;
	}
	/**
	 * 授权登录异常
	 */
	@Override
	@ExceptionHandler({AuthenticationException.class,UnauthorizedException.class})
	@ResponseBody
	public String authenticationException(Exception exception) {
//        return "error/403";
		String message = "{msg:'权限不足',code:403,status:-1}";
		if (logger.isDebugEnabled()){
			logger.debug("not have permission, active session size: {}, message: {}, exception: {}",
					sessionDAO.getActiveSessions(false).size(), message, exception);
		}
		return message;
	}
	/**
	 * 未登录异常
	 */
	@ExceptionHandler({Exception.class})
	@ResponseBody
	public String unsupportedTokenException(Exception exception) {
//        return "error/403";
		String message = "{msg:'未登录',code:403,status:-4}";
		if (logger.isDebugEnabled()){
			logger.debug("not logined, active session size: {}, message: {}, exception: {}",
					sessionDAO.getActiveSessions(false).size(), message, exception);
		}
		return message;
	}
	/**
	 * 参数绑定异常
	 */
	@ExceptionHandler({Throwable.class})
	@ResponseBody
	public String throwableException(Exception throwable) {
//		return "error/400";
		String message = "{msg:'系统错误',code:500,status:-2}";
		/*if (logger.isDebugEnabled()){
			logger.debug("login fail, active session size: {}, message: {}, exception: {}",
					sessionDAO.getActiveSessions(false).size(), message, throwable);
		}*/
		return message;
	}
	
}
