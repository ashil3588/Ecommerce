package com.example.library.service.impl;

import com.example.library.Exception.OtpSendException;
import com.example.library.service.MessageService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Random;

@Service
public class MessageServiceImpl implements MessageService {
    @Override
    public String generateOtp() {
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
    }

    @Override
    public void sendOtp(String otp) {
        try {
            String phoneNumber="+19282638001";
            Twilio.init(System.getenv("AccountSID"),System.getenv("AuthToken"));
            PhoneNumber to = new PhoneNumber("+918848563424");//to
            PhoneNumber from = new PhoneNumber(phoneNumber); // from
            String otpMessage = "Dear Customer , Your OTP is  " + otp + " for sending sms through Spring boot application. Thank You.";
            Message message = Message
                    .creator(to, from,
                            otpMessage)
                    .create();
        } catch (Exception e) {
            throw new OtpSendException("Error sending OTP: " + e.getMessage()) ;
        }

    }

}

