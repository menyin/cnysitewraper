/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.config.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cny.cnysite.common.config.Global;
import com.cny.cnysite.common.persistence.Page;
import com.cny.cnysite.common.web.BaseController;
import com.cny.cnysite.common.utils.StringUtils;
import com.cny.cnysite.modules.config.entity.WsExFaretemplate;
import com.cny.cnysite.modules.config.service.WsExFaretemplateService;

/**
 * 快递模板管理Controller
 * @author cny
 * @version 2018-07-12
 */
@Controller
@RequestMapping(value = "${adminPath}/config/wsExFaretemplate")
public class WsExFaretemplateController extends BaseController {

	@Autowired
	private WsExFaretemplateService wsExFaretemplateService;
	
	@ModelAttribute
	public WsExFaretemplate get(@RequestParam(required=false) String id) {
		WsExFaretemplate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wsExFaretemplateService.get(id);
		}
		if (entity == null){
			entity = new WsExFaretemplate();
		}
		return entity;
	}
	
	@RequiresPermissions("config:wsExFaretemplate:view")
	@RequestMapping(value = {"list", ""})
	public String list(WsExFaretemplate wsExFaretemplate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WsExFaretemplate> page = wsExFaretemplateService.findPage(new Page<WsExFaretemplate>(request, response), wsExFaretemplate); 
		model.addAttribute("page", page);
		return "modules/config/wsExFaretemplateList";
	}

	@RequiresPermissions("config:wsExFaretemplate:view")
	@RequestMapping(value = "form")
	public String form(WsExFaretemplate wsExFaretemplate, Model model) {
		model.addAttribute("wsExFaretemplate", wsExFaretemplate);
		return "modules/config/wsExFaretemplateForm";
	}

	@RequiresPermissions("config:wsExFaretemplate:edit")
	@RequestMapping(value = "save")
	public String save(WsExFaretemplate wsExFaretemplate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wsExFaretemplate)){
			return form(wsExFaretemplate, model);
		}
		wsExFaretemplateService.save(wsExFaretemplate);
		addMessage(redirectAttributes, "保存快递模板成功");
		return "redirect:"+Global.getAdminPath()+"/config/wsExFaretemplate/?repage";
	}
	
	@RequiresPermissions("config:wsExFaretemplate:edit")
	@RequestMapping(value = "delete")
	public String delete(WsExFaretemplate wsExFaretemplate, RedirectAttributes redirectAttributes) {
		wsExFaretemplateService.delete(wsExFaretemplate);
		addMessage(redirectAttributes, "删除快递模板成功");
		return "redirect:"+Global.getAdminPath()+"/config/wsExFaretemplate/?repage";
	}

}