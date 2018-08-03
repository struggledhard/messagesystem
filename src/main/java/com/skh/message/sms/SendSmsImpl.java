package com.skh.message.sms;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by User: skh.
 * Date: 2018/7/30 Time: 14:26.
 * Description: 短息发送接口实现类
 */
@Component
public class SendSmsImpl implements SendSms {
    private final Logger logger = LoggerFactory.getLogger(SendSmsImpl.class);


    @Override
    public SendSmsResponse sendSms(String phoneNum, String signName, String templateCode,
                                   String templateParam, String smsUpExtendCode, String outId) {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 初始化acsClient，暂不支持多region化
        try {
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", SmsConfig.accessKeyId, SmsConfig.accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", SmsConfig.product, SmsConfig.domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            // 组装请求对象
            SendSmsRequest request = new SendSmsRequest();
            // 必填-待发送的手机号
            request.setPhoneNumbers(phoneNum);
            // 必填-短信签名
            request.setSignName(signName);
            // 必填-短信模板ID
            request.setTemplateCode(templateCode);
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,
            // 比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
            // 例如："{\"name\":\"Tom\", \"code\":\"123\"}"
            request.setTemplateParam(templateParam);
            //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
            request.setSmsUpExtendCode(smsUpExtendCode);
            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            request.setOutId(outId);

            SendSmsResponse response = acsClient.getAcsResponse(request);
            logger.info("短信已发送!", response.getCode());
            return response;
        } catch (ClientException e) {
            e.printStackTrace();
            logger.error("发送异常", e);
        }
        return null;
    }

    // 批量发送
    @Override
    public SendBatchSmsResponse sendBatchSms(String phoneNumJson, String signNameJson, String templateCode,
                                             String templateParamJson, String smsUpExtendCodeJson) {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化ascClient,暂时不支持多region（请勿修改）
        try {
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", SmsConfig.accessKeyId,
                    SmsConfig.accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", SmsConfig.product, SmsConfig.domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            //组装请求对象
            SendBatchSmsRequest request = new SendBatchSmsRequest();
            //使用post提交
            //request.setMethod(MethodType.POST);
            //必填:待发送手机号。支持JSON格式的批量调用，批量上限为100个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
            //request.setPhoneNumberJson("[\"1500000000\",\"1500000001\"]");
            request.setPhoneNumberJson(phoneNumJson);
            //必填:短信签名-支持不同的号码发送不同的短信签名
            //request.setSignNameJson("[\"云通信\",\"云通信\"]");
            request.setSignNameJson(signNameJson);
            //必填:短信模板-可在短信控制台中找到
            //request.setTemplateCode("SMS_1000000");
            request.setTemplateCode(templateCode);
            //必填:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
            //request.setTemplateParamJson("[{\"name\":\"Tom\", \"code\":\"123\"},{\"name\":\"Jack\", \"code\":\"456\"}]");
            request.setTemplateParamJson(templateParamJson);
            //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCodeJson("[\"90997\",\"90998\"]");
            //request.setSmsUpExtendCodeJson(smsUpExtendCodeJson);

            SendBatchSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            return sendSmsResponse;
        } catch (ClientException e) {
            logger.error("发生异常", e);
        }
        return null;
    }
}
