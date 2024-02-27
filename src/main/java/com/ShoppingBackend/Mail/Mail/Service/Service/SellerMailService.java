package com.ShoppingBackend.Mail.Mail.Service.Service;

import com.ShoppingBackend.Mail.Mail.Service.Utils.MailUtils;
import com.ShoppingBackend.Mail.Mail.Service.Utils.ShoppingLogger;
import com.ShoppingBackend.Mail.Mail.Service.DTO.RequestDTO.AddProductDTO;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SellerMailService {
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    MailUtils mailUtils;
    public void sendAddProductMail(AddProductDTO addProductDTO) throws Exception{
        ShoppingLogger.logger.info("AddProductDTO RequestBody : " + addProductDTO.toString());
        ShoppingLogger.logger.info("Setting up mail configuration");
        String emailId = addProductDTO.getMailId();
        String subject = addProductDTO.getSubjectLine();

        MimeMessage message = mailUtils.getMimeMessage();
        //JavaMailSender mailSender = mailUtils.getJavaMailSender();
        ShoppingLogger.logger.info("Mail configuration setup done");
        MimeMessageHelper helper = mailUtils.getMimeMessageHelper();
        try{
            helper.setTo(emailId);
            helper.setSubject(subject);
            helper.setText(addProductDTO.getMailMessage());
            ShoppingLogger.logger.info("Mail Sent successfully");
            mailSender.send(message);
            ShoppingLogger.logger.info("Mail Sent successfully");
        }catch (Exception e){
            ShoppingLogger.logger.info("In exception block");
            ShoppingLogger.logger.info(e.getMessage());
            throw e;
        }
    }
}
