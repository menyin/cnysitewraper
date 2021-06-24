/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.prod.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cny.cnysite.modules.prod.entity.*;
import com.cny.cnysite.modules.prod.service.WsBrandService;
import com.cny.cnysite.modules.prod.service.WsProdAttributeService;
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
import com.cny.cnysite.modules.prod.service.WsProductService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品管理Controller
 *
 * @author cny
 * @version 2018-07-13
 */
@Controller
@RequestMapping(value = "${adminPath}/prod/wsProduct")
public class WsProductController extends BaseController {

    @Autowired
    private WsProdAttributeService wsProdAttributeService;

    @Autowired
    private WsBrandService wsBrandService;

    @Autowired
    private WsProductService wsProductService;

    @ModelAttribute
    public WsProduct get(@RequestParam(required = false) String id) {
        WsProduct entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = wsProductService.get(id);
        }
        if (entity == null) {
            entity = new WsProduct();
        }
        return entity;
    }

    @RequiresPermissions("prod:wsProduct:view")
    @RequestMapping(value = {"list", ""})
    public String list(WsProduct wsProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<WsProduct> page = wsProductService.findPage(new Page<WsProduct>(request, response), wsProduct);
        model.addAttribute("page", page);
        return "modules/prod/wsProductList";
    }

    @RequiresPermissions("prod:wsProduct:view")
    @RequestMapping(value = "form")
    public String form(WsProduct wsProduct, Model model) {
        List<WsBrand> wsBrandList = wsBrandService.findList(new WsBrand());
        WsProdAttribute wsProdAttribute = new WsProdAttribute();
        wsProdAttribute.setProdCategory(wsProduct.getProdCategory());
        List<WsProdAttribute> wsProdAttributelistAV = wsProdAttributeService.findListAV(wsProdAttribute);
        for (WsProdAttribute wsProdAttributeEntity : wsProdAttributelistAV) {
            for (WsProdAttrivalue wsProdAttrivalueEntity : wsProdAttributeEntity.getWsProdAttrivalueList()) {
                for (WsProdSkuAttr wsProdSkuAttrEntity : wsProduct.getProdSkuAttrList()) {
                    if (wsProdAttrivalueEntity.getId().equals(wsProdSkuAttrEntity.getAttrbuteValue())) {
                        System.out.println(11);
                    }
                }
            }
        }

        model.addAttribute("wsProdAttributelistAV", wsProdAttributelistAV);
        model.addAttribute("wsProduct", wsProduct);
        model.addAttribute("wsBrandList", wsBrandList);
        return "modules/prod/wsProductForm";
    }

    @RequiresPermissions("prod:wsProduct:edit")
    @RequestMapping(value = "save")
    public String save(WsProduct wsProduct, Model model, RedirectAttributes redirectAttributes) {
        wsProduct.setProdSkuList(wsProduct.getProdSkuListNew());//防止被ModelAttribute影响
        wsProduct.setProdSkuAttrList(wsProduct.getProdSkuAttrListNew());//防止被ModelAttribute影响

        if (!beanValidator(model, wsProduct)) {
            return form(wsProduct, model);
        }
        wsProductService.save(wsProduct);
        addMessage(redirectAttributes, "保存产品成功");
        return "redirect:" + Global.getAdminPath() + "/prod/wsProduct/?repage";
    }

    @RequiresPermissions("prod:wsProduct:edit")
    @RequestMapping(value = "delete")
    public String delete(WsProduct wsProduct, RedirectAttributes redirectAttributes) {
        wsProductService.delete(wsProduct);
        addMessage(redirectAttributes, "删除产品成功");
        return "redirect:" + Global.getAdminPath() + "/prod/wsProduct/?repage";
    }

}