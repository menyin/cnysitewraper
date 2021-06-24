package com.cny.cnysite.modules.sys.web;

import com.cny.cnysite.common.security.shiro.session.SessionDAO;
import com.cny.cnysite.common.utils.StringUtils;
import com.cny.cnysite.common.web.ApiBaseController;
import com.cny.cnysite.modules.sys.security.SystemAuthorizingRealm;
import com.cny.cnysite.modules.sys.security.UsernamePasswordToken;
import com.cny.cnysite.modules.sys.utils.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;


@Controller
@RequestMapping("${adminPath}/api")
public class ApiLoginController extends ApiBaseController {

    @ResponseBody
    @RequestMapping("/doLogin")
    public Object doLogin(UsernamePasswordToken token) {
        SystemAuthorizingRealm.Principal principal = UserUtils.getPrincipal();
        // 如果已经登录，则返回
        if (principal != null) {
            return "{msg:'已登录',code:200,status:2}";
        }

        Subject subject = SecurityUtils.getSubject();
//        AuthenticationToken token = new UsernamePasswordToken(username, password, rememberMe, host, captcha, true);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            return "{msg:'用户名不存在',code:403,status:-1}";
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            return "{msg:'密码错误',code:403,status:-2}";
        } catch (LockedAccountException e) {
            e.printStackTrace();
            return "{msg:'帐号锁定 ',code:403,status:-3}";
        } catch (DisabledAccountException e) {
            e.printStackTrace();
            return "{msg:'用户禁用',code:403,status:-4}";
        } catch (ExcessiveAttemptsException e) {
            e.printStackTrace();
            return "{msg:'登录重试次数超限',code:403,status:-5}";
        } catch (ConcurrentAccessException e) {
            e.printStackTrace();
            return "{msg:'一个用户多次登录异常',code:403,status:-6}";
        } catch (AccountException e) {
            e.printStackTrace();
            return "{msg:'账户异常',code:403,status:-7}";
        } catch (ExpiredCredentialsException e) {
            e.printStackTrace();
            return "{msg:'过期的凭据异常 ',code:403,status:-8}";
        } catch (CredentialsException e) {
            e.printStackTrace();
            return "{msg:'凭据异常  ',code:403,status:-9}";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "{msg:'登录成功',code:200,status:1}";
    }


}
