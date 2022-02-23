package com.dizhongdi.servicemsm.service.impl;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.dizhongdi.servicemsm.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * ClassName:MsmServiceImpl
 * Package:com.dizhongdi.servicemsm.service.impl
 * Description:
 *
 * @Date: 2022/2/19 22:03
 * @Author:dizhongdi
 */
@Service
public class MsmServiceImpl implements MsmService {
    @Autowired
    JavaMailSender javaMailSender;

    //容联云发送手机验证码
    @Override
    public boolean send(String phone, String code) {
        //生产环境请求地址：app.cloopen.com
        String serverIp = "app.cloopen.com";
        //请求端口
        String serverPort = "8883";
        //主账号,登陆云通讯网站后,可在控制台首页看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN
        String accountSId = "8aaf07087a331dc7017b0aaa2bde4088";
        String accountToken = "70955fe6dc7542d789e5e6cd082c8a29";
        //请使用管理控制台中已创建应用的APPID
        String appId = "8aaf07087a331dc7017b0aaa2c9c408e";
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSId, accountToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_JSON);
        //发送短信至手机号
        String to = phone;
        //短信模板
        String templateId= "1";
        //这里模拟一下验证码123456，10分钟内到期
        String[] datas = {code,"5"};
        HashMap<String, Object> result = sdk.sendTemplateSMS(to,templateId,datas);
        if("000000".equals(result.get("statusCode"))){
            //正常返回输出data包体信息（map）
            return true;

//            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
//            Set<String> keySet = data.keySet();
//            for(String key:keySet){
//                Object object = data.get(key);
//                System.out.println(key +" = "+object);
//            }
        }else{
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
            return false;
        }
    }

    @Override
    public String getCode() {
            int random = (int) (Math.random() * 1000000);
            System.out.println(random);
            String code = String.format("%06d", random);
            System.out.println(code);
            return code;
    }

    @Override
    @Async
    public void sendEmail(String email, String code) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("尚医通项目登录验证码");
        simpleMailMessage.setText("尊敬的:"+email+"您的注册校验验证码为：" + code + "有效期5分钟");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom("2755063993@qq.com");
        javaMailSender.send(simpleMailMessage);
    }


}
