/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.prod.web;

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
import com.cny.cnysite.modules.prod.entity.WsProdSku;
import com.cny.cnysite.modules.prod.service.WsProdSkuService;

/**
 * 产品sku表管理Controller
 * @author cny
 * @version 2018-07-14
 */
@Controller
@RequestMapping(value = "${adminPath}/prod/wsProdSku")
public class WsProdSkuController extends BaseController {

	@Autowired
	private WsProdSkuService wsProdSkuService;
	
	@ModelAttribute
	public WsProdSku get(@RequestParam(required=false) String id) {
		WsProdSku entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wsProdSkuService.get(id);
		}
		if (entity == null){
			entity = new WsProdSku();
		}
		return entity;
	}
	
	@RequiresPermissions("prod:wsProdSku:view")
	@RequestMapping(value = {"list", ""})
	public String list(WsProdSku wsProdSku, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WsProdSku> page = wsProdSkuService.findPage(new Page<WsProdSku>(request, response), wsProdSku); 
		model.addAttribute("page", page);
		return "modules/prod/wsProdSkuList";
	}

	@RequiresPermissions("prod:wsProdSku:view")
	@RequestMapping(value = "form")
	public String form(WsProdSku wsProdSku, Model model) {
		model.addAttribute("wsProdSku", wsProdSku);
		return "modules/prod/wsProdSkuForm";
	}

	@RequiresPermissions("prod:wsProdSku:edit")
	@RequestMapping(value = "save")
	public String save(WsProdSku wsProdSku, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wsProdSku)){
			return form(wsProdSku, model);
		}
		wsProdSkuService.save(wsProdSku);
		addMessage(redirectAttributes, "保存产品sku成功");
		return "redirect:"+Global.getAdminPath()+"/prod/wsProdSku/?repage";
	}
	
	@RequiresPermissions("prod:wsProdSku:edit")
	@RequestMapping(value = "delete")
	public String delete(WsProdSku wsProdSku, RedirectAttributes redirectAttributes) {
		wsProdSkuService.delete(wsProdSku);
		addMessage(redirectAttributes, "删除产品sku成功");
		return "redirect:"+Global.getAdminPath()+"/prod/wsProdSku/?repage";
	}

}