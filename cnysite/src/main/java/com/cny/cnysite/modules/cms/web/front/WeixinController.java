/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.cms.web.front;

import javax.servlet.http.HttpServletRequest;

import com.cny.cnysite.common.web.BaseController;
import com.cny.cnysite.modules.cms.entity.WxOutMsg;
import com.cny.cnysite.modules.cms.entity.WxReceiveMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cny.cnysite.modules.cms.utils.WiexinSignUtil;

import java.util.Date;

/**
 * 测试Controller
 * @author ThinkGem
 * @version 2014-02-28
 */
@Controller
@RequestMapping(value = "${frontPath}/weixin")
public class WeixinController extends BaseController {

	/**
	 * 
	 * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。 
	 * @param timestamp 时间戳
	 * @param nonce 随机数 
	 * @param echostr 随机数 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public String get(String signature, String timestamp, String nonce, String echostr, HttpServletRequest request) {
		
		System.out.println("=============================================== get start");
		for (Object o : request.getParameterMap().keySet()){
			System.out.println(o + " = " + request.getParameter((String)o));
		}
		System.out.println("=============================================== get end");
		
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
        if (WiexinSignUtil.checkSignature(signature, timestamp, nonce)) {  
        	return echostr;
        }

		return "";
	}

	/*@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public String post(String signature, String timestamp, String nonce, String echostr, HttpServletRequest request) {
		System.out.println("=============================================== post start");
		for (Object o : request.getParameterMap().keySet()){
			System.out.println(o + " = " + request.getParameter((String)o));
		}
		System.out.println("=============================================== post end");
		StringBuilder result = new StringBuilder();
		result.append("<xml>" +
				"<ToUserName><![CDATA[toUser]]></ToUserName>" +
				"<FromUserName><![CDATA[fromUser]]></FromUserName>" +
				"<CreateTime>12345678</CreateTime>" +
				"<MsgType><![CDATA[text]]></MsgType>" +
				"<Content><![CDATA[你好]]></Content>" +
				"</xml>");
		return result.toString();
	}*/

	@RequestMapping(value = "", method = RequestMethod.POST, produces={"text/xml; charset=UTF-8"})//注意这个produces
	@ResponseBody
	public Object post(String signature, String timestamp, String nonce, String echostr,@RequestBody WxReceiveMsg wxReceiveMsg) {
		if (wxReceiveMsg.getMsgType().equals("text")) {
			WxOutMsg wxOutMsg = new WxOutMsg();
			wxOutMsg.setFromUserName(wxReceiveMsg.getToUserName());
			wxOutMsg.setToUserName(wxReceiveMsg.getFromUserName());
			wxOutMsg.setContent(wxReceiveMsg.getContent());
			wxOutMsg.setMsgType(wxReceiveMsg.getMsgType());
			wxOutMsg.setCreateTime(new Date().getTime());
			return wxOutMsg;
		}else if(wxReceiveMsg.getMsgType().equals("image")){
			WxOutMsg wxOutMsg = new WxOutMsg();
			wxOutMsg.setFromUserName(wxReceiveMsg.getToUserName());
			wxOutMsg.setToUserName(wxReceiveMsg.getFromUserName());
			wxOutMsg.setMsgType(wxReceiveMsg.getMsgType());
			wxOutMsg.setCreateTime(new Date().getTime());
			wxOutMsg.setMediaId(new String[]{wxReceiveMsg.getMediaId()});
			System.out.println(wxReceiveMsg);
			return wxOutMsg;
		}else if(wxReceiveMsg.getMsgType().equals("event")){
			if (wxReceiveMsg.getEvent().equals("subscribe")) {
				WxOutMsg wxOutMsg = new WxOutMsg();
				wxOutMsg.setFromUserName(wxReceiveMsg.getToUserName());
				wxOutMsg.setToUserName(wxReceiveMsg.getFromUserName());
				wxOutMsg.setContent("~~欢迎来到永恒国度~~");
				wxOutMsg.setMsgType("text");
				wxOutMsg.setCreateTime(new Date().getTime());
				System.out.println(wxReceiveMsg);
				return wxOutMsg;
			}
		}
		return null;
	}


}
