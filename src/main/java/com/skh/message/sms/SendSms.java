package com.skh.message.sms;

import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;

/**
 * Created by User: admin.
 * Date: 2018/7/30 Time: 14:12.
 * Description: 发送短信接口
 * 运用阿里短信sdk
 */
public interface SendSms {
    SendSmsResponse sendSms(String phoneNum, String signName, String templateCode,
                            String templateParam, String smsUpExtendCode, String outId);

    SendBatchSmsResponse sendBatchSms(String phoneNumJson, String signNameJson, String templateCode,
                                      String templateParamJson, String smsUpExtendCodeJson);
}
