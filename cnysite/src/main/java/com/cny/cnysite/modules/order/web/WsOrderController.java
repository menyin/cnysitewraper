/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.order.web;

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
import com.cny.cnysite.modules.order.entity.WsOrder;
import com.cny.cnysite.modules.order.service.WsOrderService;

/**
 * 订单管理Controller
 * @author cny
 * @version 2018-07-24
 */
@Controller
@RequestMapping(value = "${adminPath}/order/wsOrder")
public class WsOrderController extends BaseController {

	@Autowired
	private WsOrderService wsOrderService;
	
	@ModelAttribute
	public WsOrder get(@RequestParam(required=false) String id) {
		WsOrder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = wsOrderService.get(id);
		}
		if (entity == null){
			entity = new WsOrder();
		}
		return entity;
	}
	
	@RequiresPermissions("order:wsOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(WsOrder wsOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WsOrder> page = wsOrderService.findPage(new Page<WsOrder>(request, response), wsOrder); 
		model.addAttribute("page", page);
		return "modules/order/wsOrderList";
	}

	@RequiresPermissions("order:wsOrder:view")
	@RequestMapping(value = "form")
	public String form(WsOrder wsOrder, Model model) {
		model.addAttribute("wsOrder", wsOrder);
		return "modules/order/wsOrderForm";
	}

	@RequiresPermissions("order:wsOrder:edit")
	@RequestMapping(value = "save")
	public String save(WsOrder wsOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wsOrder)){
			return form(wsOrder, model);
		}
		wsOrderService.save(wsOrder);
		addMessage(redirectAttributes, "保存订单成功");
		return "redirect:"+Global.getAdminPath()+"/order/wsOrder/?repage";
	}
	
	@RequiresPermissions("order:wsOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(WsOrder wsOrder, RedirectAttributes redirectAttributes) {
		wsOrderService.delete(wsOrder);
		addMessage(redirectAttributes, "删除订单成功");
		return "redirect:"+Global.getAdminPath()+"/order/wsOrder/?repage";
	}

}