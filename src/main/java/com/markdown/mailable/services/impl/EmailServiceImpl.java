package com.markdown.mailable.services.impl;

import com.markdown.mailable.services.EmailService;
import com.markdown.mailable.utils.CssInliner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {

        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            Context context = new Context();
            Map<String, Object> model = new HashMap<>();
            model.put("name", "Developer!");
            model.put("location", "Vietnam");
            model.put("sign", "Java Developer");


            context.setVariables(model);
            String html = templateEngine.process("mail", context);
            String template = CssInliner.inlineCss(ResourceUtils.getFile("classpath:templates/theme/default.css"),
                    html);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(template, true);
            emailSender.send(message);
        } catch (MessagingException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
