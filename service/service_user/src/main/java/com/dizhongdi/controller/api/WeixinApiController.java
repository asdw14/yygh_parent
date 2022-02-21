package com.dizhongdi.controller.api;

import com.dizhongdi.result.Result;
import com.dizhongdi.utils.ConstantWXPropertiesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * ClassName:WeixinApiController
 * Package:com.dizhongdi.controller.api
 * Description:
 *
 * @Date: 2022/2/20 21:13
 * @Author:dizhongdi
 */
@Controller
@RequestMapping("/api/ucenter/wx")
public class WeixinApiController {

    //获取微信登录参数
    @GetMapping("getLoginParam")
    @ResponseBody
    public Result genQrConnect(){
        try {
            HashMap<String, Object> map = new HashMap<>();
            String redirectUri = URLEncoder.encode(ConstantWXPropertiesUtil.WX_OPEN_REDIRECT_URL, "UTF-8");
            map.put("appid", ConstantWXPropertiesUtil.WX_OPEN_APP_ID);
            map.put("redirectUri", redirectUri);
            map.put("scope", "snsapi_login");
            map.put("state", System.currentTimeMillis()+"");//System.currentTimeMillis()+""

            return Result.ok(map);
        } catch (UnsupportedEncodingException e) {

            return null;
        }


    }
}
