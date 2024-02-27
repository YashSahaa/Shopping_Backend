package com.ShoppingBackend.Mail.Mail.Service.Utils;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailUtils {
    JavaMailSender javaMailSender;
    MimeMessageHelper mimeMessageHelper;
    MimeMessage mimeMessage;
    public MailUtils(){
        javaMailSender = new JavaMailSenderImpl();
        mimeMessage = javaMailSender.createMimeMessage();
        mimeMessageHelper = new MimeMessageHelper(mimeMessage);
    }

    public JavaMailSender getJavaMailSender() {
        return javaMailSender;
    }

    public MimeMessageHelper getMimeMessageHelper() {
        return mimeMessageHelper;
    }

    public MimeMessage getMimeMessage() {
        return mimeMessage;
    }
}
