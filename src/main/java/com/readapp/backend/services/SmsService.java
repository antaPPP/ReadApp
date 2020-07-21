package com.readapp.backend.services;

import com.readapp.backend.exceptions.SMSException;
import com.readapp.backend.models.utils.SMSForm;

public interface SmsService {
    String sendVerificationSMS(SMSForm form) throws SMSException;
}
