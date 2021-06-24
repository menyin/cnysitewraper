package com.cny.cnysite.modules.inter.web;

import com.cny.cnysite.common.web.BaseController;
import com.cny.cnysite.modules.cms.entity.Site;
import com.cny.cnysite.modules.cms.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*测试微信小程序*/
@Controller
@RequestMapping("${wxPath}/wcx")
public class TestWcx extends BaseController {
    @Autowired
    private SiteService siteService;

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getSiteList")
    public  List<Site> getSiteList(){
        List<Site> siteList =siteService.findList(new Site());
        return siteList;
    }
}
