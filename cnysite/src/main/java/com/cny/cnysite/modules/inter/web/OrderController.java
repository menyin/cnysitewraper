package com.cny.cnysite.modules.inter.web;

import com.cny.cnysite.common.utils.StringUtils;
import com.cny.cnysite.common.web.BaseController;
import com.cny.cnysite.modules.order.entity.WsOrder;
import com.cny.cnysite.modules.pay.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping(value = "${wxPath}/order")
public class OrderController extends BaseController {

    /**
     * 扫码支付
     * @param wsOrder
     * @param request
     * @return
     */
    @RequestMapping("/preOrder")
    @ResponseBody
    @CrossOrigin
    public Map preOrder(WsOrder wsOrder, HttpServletRequest request){
        try {
            String nonce_str=UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
            WXPayConfig wxPayConfig = WXPayConfigImpl.getInstance();
            WXPay wxpay = new WXPay(wxPayConfig);
            HashMap<String, String> preData = new HashMap<String, String>();
            preData.put("appid","wx67b89ec78e12e429");
            preData.put("mch_id","1418984102");
            preData.put("device_info","WEB");
            preData.put("nonce_str",nonce_str);
            preData.put("sign_type", WXPayConstants.HMACSHA256);
            preData.put("body","测试商品");
            preData.put("detail","商品详情");
            preData.put("attach","自定义参数");
            preData.put("out_trade_no","123419");
            preData.put("fee_type","CNY");
            preData.put("total_fee","1");
            preData.put("spbill_create_ip",request.getLocalAddr());
            preData.put("time_start","20180726091010");
            preData.put("time_expire","20180727091010");
            preData.put("goods_tag","WXG");
            preData.put("notify_url","http://cny.s1.natapp.cc/cnysite/wx/order/notify_url");
            preData.put("trade_type","NATIVE");
            preData.put("product_id","product123456");
//            preData.put("limit_pay","no_credit");//可不传
            preData.put("scene_info","{\"store_info\" : {\n" +
                    "\"id\": \"SZTX001\",\n" +
                    "\"name\": \"腾大餐厅\",\n" +
                    "\"area_code\": \"440305\",\n" +
                    "\"address\": \"科技园中一路腾讯大厦\" }}");
            Map<String, String> r = wxpay.unifiedOrder(preData);
            System.out.println(r);
            return r;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * h5支付
     * @param wsOrder
     * @param request
     * @return
     */
    @RequestMapping("/h5preOrder")
    @ResponseBody
    @CrossOrigin
    public Map h5preOrder(WsOrder wsOrder, HttpServletRequest request){
        try {
            String nonce_str=UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
            WXPayConfig wxPayConfig = WXPayConfigImpl.getInstance();
            WXPay wxpay = new WXPay(wxPayConfig);
            HashMap<String, String> preData = new HashMap<String, String>();
            preData.put("appid","wx67b89ec78e12e429");
            preData.put("mch_id","1418984102");
            preData.put("device_info","h5devic");//非必需
            preData.put("nonce_str",nonce_str);
            preData.put("sign_type", WXPayConstants.HMACSHA256);
            preData.put("body","测试h5支付商品");
            preData.put("detail","商品详情");
            preData.put("attach","自定义参数");
            preData.put("out_trade_no","654321");
            preData.put("fee_type","CNY");
            preData.put("total_fee","1");
            preData.put("spbill_create_ip",request.getLocalAddr());
            preData.put("time_start","20180726091010");
            preData.put("time_expire","20180727091010");
            preData.put("goods_tag","WXG");
            preData.put("notify_url","http://cny.s1.natapp.cc/cnysite/wx/order/notify_url");
            preData.put("trade_type","MWEB");
            preData.put("product_id","product123456");
//            preData.put("limit_pay","no_credit");//可不传
            preData.put("scene_info","{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": \"https://pay.qq.com\",\"wap_name\": \"测试h5支付\"}}");
            Map<String, String> r = wxpay.unifiedOrder(preData);
            System.out.println(r);
            return r;
        } catch (Exception e) {
            return null;
        }
    }
//    @RequestMapping(value = "", method = RequestMethod.POST, produces={"text/xml; charset=UTF-8"})//注意这个produces
//    @RequestMapping(value = "/notify_url",headers = {"content-type=application/xml"})
//    @RequestMapping(value = "/notify_url", consumes = "application/xml")
    @RequestMapping(value = "/notify_url")
    @ResponseBody
    @CrossOrigin
    public String notify_url(HttpServletRequest request, HttpServletResponse response){
        String result=setXml("fail","服务器异常情况");;//返回给微信的处理结果
        try{
           String inputLine;
           String notityXml = "";
           request.setCharacterEncoding("UTF-8");
           response.setCharacterEncoding("UTF-8");
           response.setContentType("text/html;charset=UTF-8");
           response.setHeader("Access-Control-Allow-Origin", "*");
           //微信给返回的东西
           while ((inputLine = request.getReader().readLine()) != null) {
               notityXml += inputLine;
           }
           request.getReader().close();
           if (StringUtils.isEmpty(notityXml)) {
               result = setXml("fail","xml为空");
           }
            Map params = WXPayUtil.xmlToMap(notityXml);

            //判断map是否为空或size为零
           //用map和商户秘钥验证签名
           String key = "224fe994cc962968ca12973c3fa949b5";
           if(params==null||params.size()==0|| !WXPayUtil.isSignatureValid(params,key)){
               HashMap<String, String> errorMap = new HashMap<String, String>();
               errorMap.put("return_code", "FAIL");
               errorMap.put("return_msg", "请求数据有误或签名失败");
//               return  errorMap;
               result = setXml("FAIL","请求数据有误或签名失败");
               return result;
           }

           //判断订单状态是否是未支付，如已支付则直接返回
           //验证支付金额与订单金额是否一致
           System.out.println("如有问题则返回errorMap");

           //将更新订单表的相关字段，如付款时间、状态、
           //减库存（减少sku表的数量字段）、赠送积分，优惠券等
           //按固定格式返回给微信反馈
            System.out.println("以上所有操作都需要在service层处理");

           HashMap<String, String> okMap = new HashMap<String, String>();
           okMap.put("return_code","SUCCESS");
           okMap.put("return_msg","OK");
           System.out.println("执行回调");
//           return okMap;
            result = setXml("SUCCESS", "OK");
            return result;
       }catch(Exception e){
           HashMap<String, String> errorMap = new HashMap<String, String>();
           errorMap.put("return_code", "FAIL");
           errorMap.put("return_msg", "程序出现异常");
//           return  errorMap;
            return result;
        }
    }
    //通过xml 发给微信消息
    public static String setXml(String return_code, String return_msg) {
        SortedMap<String, String> parameters = new TreeMap<String, String>();
        parameters.put("return_code", return_code);
        parameters.put("return_msg", return_msg);
        return "<xml><return_code><![CDATA[" + return_code + "]]>" +
                "</return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
    }
}
