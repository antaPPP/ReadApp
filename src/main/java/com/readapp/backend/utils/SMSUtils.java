package com.readapp.backend.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.readapp.backend.config.AliyunConfig;
import com.readapp.backend.models.utils.SMSForm;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.Future;

public class SMSUtils {

    public static Future<String> sendVerificationSMS(SMSForm form) {
        System.out.println(AliyunConfig.ACCESS_KEY_ID);
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", AliyunConfig.ACCESS_KEY_ID, AliyunConfig.ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", form.getMobile());
        request.putQueryParameter("SignName", "已读App");
        request.putQueryParameter("TemplateCode", AliyunConfig.VERIFICATION_SMS_TEMPLATE);
        request.putQueryParameter("TemplateParam", "{\"code\":" + "\"" + form.getCode() + "\"" +
                "}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
            return new AsyncResult<>("SMS failed due to Server Exception");
        } catch (ClientException e) {
            e.printStackTrace();
            return new AsyncResult<>("SMS failed due to Client Exception");
        }

        return new AsyncResult<>("SMS sent");
    }
}
