package com.cny.cnysite.modules.cms.api;

import com.cny.cnysite.common.web.ApiBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("${adminPath}/cms/api/test")
public class ApiTestController extends ApiBaseController {
    @RequiresPermissions("cms:api:test:list")
    @RequestMapping(value = {"list", ""})
    @ResponseBody
    public Object list() {

        return "{code:200,status:3,data:123123}";
    }
}
