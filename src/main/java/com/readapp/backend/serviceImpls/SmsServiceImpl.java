package com.readapp.backend.serviceImpls;

import com.readapp.backend.exceptions.SMSException;
import com.readapp.backend.models.utils.SMSForm;
import com.readapp.backend.services.SmsService;
import com.readapp.backend.utils.RNG;
import org.springframework.stereotype.Service;

@Service(value = "smsService")
public class SmsServiceImpl implements SmsService {
    @Override
    public String sendVerificationSMS(SMSForm form) throws SMSException {
        String code = RNG.generateVerificationCode(6);
        form.setCode(code);
        System.out.println(code);
        //SMSUtils.sendVerificationSMS(form);
        return code;
    }
}
