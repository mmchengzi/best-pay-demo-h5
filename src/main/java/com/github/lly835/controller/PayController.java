package com.github.lly835.controller;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.*;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.lly835.bestpay.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Random;

/**
 * 支付相关
 * @version 1.0 2017/3/2
 * @auther <a href="mailto:lly835@163.com">廖师兄</a>
 * @since 1.0
 */
@Controller
@Slf4j
public class PayController {

    @Autowired
    private BestPayServiceImpl bestPayService;

    /**
     * 发起支付
     */
    @GetMapping(value = "/pay")
    public ModelAndView pay(@RequestParam("openid") String openid,
                            Map<String, Object> map) {
        PayRequest request = new PayRequest();
        Random random = new Random();

        //支付请求参数
        request.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        request.setOrderId(String.valueOf(random.nextInt(1000000000)));
        request.setOrderAmount(0.01);
        request.setOrderName("最好的支付sdk");
        request.setOpenid(openid);
        log.info("【发起支付】request={}", JsonUtil.toJson(request));

        PayResponse payResponse = bestPayService.pay(request);
        log.info("【发起支付】response={}", JsonUtil.toJson(payResponse));

        map.put("payResponse", payResponse);

        return new ModelAndView("pay/create", map);
    }

    /**
     * 异步回调
     */
    @PostMapping(value = "/notify")
    public ModelAndView notify(@RequestBody String notifyData) throws Exception {
        log.info("【异步回调】request={}", notifyData);
        PayResponse response = bestPayService.asyncNotify(notifyData);
        log.info("【异步回调】response={}", JsonUtil.toJson(response));

        return new ModelAndView("pay/success");
    }
    /**
     * 订单查询
     */
    @PostMapping(value = "/query")
    public ModelAndView query(@RequestBody String orderid) throws Exception {
        OrderQueryRequest orderRequest=new OrderQueryRequest();
        orderRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        orderRequest.setOrderId(orderid);
        log.info("【订单查询】request={}", orderRequest);
        OrderQueryResponse orderQueryResponse = bestPayService.query(orderRequest);
        log.info("【订单查询】response={}", JsonUtil.toJson(orderQueryResponse));
        return new ModelAndView("pay/query");
    }
    /**
     * 退款
     */
    @PostMapping(value = "/refund")
    public ModelAndView refund(@RequestBody String orderid) throws Exception {
        RefundRequest request=new RefundRequest();
        request.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        request.setOrderId(orderid);
        log.info("【退款】request={}", request);
        RefundResponse refundResponse = bestPayService.refund(request);
        log.info("【退款】response={}", JsonUtil.toJson(refundResponse));
        return new ModelAndView("pay/refund");
    }

}
