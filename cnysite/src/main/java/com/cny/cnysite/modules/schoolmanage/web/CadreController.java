/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.schoolmanage.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.cny.cnysite.common.config.Global;
import com.cny.cnysite.common.web.BaseController;
import com.cny.cnysite.common.utils.StringUtils;
import com.cny.cnysite.modules.schoolmanage.entity.Cadre;
import com.cny.cnysite.modules.schoolmanage.service.CadreService;

/**
 * 干部管理Controller
 * @author 陈南阳
 * @version 2018-05-24
 */
@Controller
@RequestMapping(value = "${adminPath}/schoolmanage/cadre")
public class CadreController extends BaseController {

	@Autowired
	private CadreService cadreService;
	
	@ModelAttribute
	public Cadre get(@RequestParam(required=false) String id) {
		Cadre entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cadreService.get(id);
		}
		if (entity == null){
			entity = new Cadre();
		}
		return entity;
	}
	
	@RequiresPermissions("schoolmanage:cadre:view")
	@RequestMapping(value = {"list", ""})
	public String list(Cadre cadre, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Cadre> list = cadreService.findList(cadre); 
		model.addAttribute("list", list);
		return "modules/schoolmanage/cadreList";
	}

	@RequiresPermissions("schoolmanage:cadre:view")
	@RequestMapping(value = "form")
	public String form(Cadre cadre, Model model) {
		if (cadre.getParent()!=null && StringUtils.isNotBlank(cadre.getParent().getId())){
			cadre.setParent(cadreService.get(cadre.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(cadre.getId())){
				Cadre cadreChild = new Cadre();
				cadreChild.setParent(new Cadre(cadre.getParent().getId()));
				List<Cadre> list = cadreService.findList(cadre); 
				if (list.size() > 0){
					cadre.setSort(list.get(list.size()-1).getSort());
					if (cadre.getSort() != null){
						cadre.setSort(cadre.getSort() + 30);
					}
				}
			}
		}
		if (cadre.getSort() == null){
			cadre.setSort(30);
		}
		model.addAttribute("cadre", cadre);
		return "modules/schoolmanage/cadreForm";
	}

	@RequiresPermissions("schoolmanage:cadre:edit")
	@RequestMapping(value = "save")
	public String save(Cadre cadre, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cadre)){
			return form(cadre, model);
		}
		cadreService.save(cadre);
		addMessage(redirectAttributes, "保存干部成功");
		return "redirect:"+Global.getAdminPath()+"/schoolmanage/cadre/?repage";
	}
	
	@RequiresPermissions("schoolmanage:cadre:edit")
	@RequestMapping(value = "delete")
	public String delete(Cadre cadre, RedirectAttributes redirectAttributes) {
		cadreService.delete(cadre);
		addMessage(redirectAttributes, "删除干部成功");
		return "redirect:"+Global.getAdminPath()+"/schoolmanage/cadre/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Cadre> list = cadreService.findList(new Cadre());
		for (int i=0; i<list.size(); i++){
			Cadre e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}