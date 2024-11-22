package com.pado.inflow.employee.info.command.application.service;

import com.pado.inflow.common.exception.CommonException;
import com.pado.inflow.common.exception.ErrorCode;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private final DefaultMessageService messageService;

    @Value("${coolsms.api.number}")
    private String fromPhoneNumber;

    public SmsService(@Value("${coolsms.api.key}") String apiKey, @Value("${coolsms.api.secret}") String apiSecret) {
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret, "https://api.coolsms.co.kr");
    }

    /**
     * SMS 발송
     */
    public void sendSms(String toPhoneNumber, String messageText) {
        try {
            Message message = new Message();
            message.setFrom(fromPhoneNumber);
            message.setTo(toPhoneNumber);
            message.setText(messageText);

            messageService.sendOne(new SingleMessageSendingRequest(message));
        } catch (Exception e) {
            throw new CommonException(ErrorCode.SmsSendingException);
        }
    }
}
