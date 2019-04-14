package com.hendisantika.springmvcemail.controller;

import com.hendisantika.springmvcemail.dto.MailObject;
import com.hendisantika.springmvcemail.service.EmailServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 8/15/17
 * Time: 7:31 AM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/mail")
public class MailController {

    private Logger logger = LogManager.getLogger(MailController.class);

    @Autowired
    public EmailServiceImpl emailService;

    @Value("${attachment.invoice}")
    private String attachmentPath;

    @Autowired
    public SimpleMailMessage template;

    private static final Map<String, Map<String, String>> labels;

    static {
        labels = new HashMap<>();

        //Simple email
        Map<String, String> props = new HashMap<>();
        props.put("headerText", "Send Simple Email");
        props.put("messageLabel", "Message");
        props.put("additionalInfo", "");
        labels.put("send", props);

        //Email with template
        props = new HashMap<>();
        props.put("headerText", "Send Email Using Template");
        props.put("messageLabel", "Template Parameter");
        props.put("additionalInfo",
                "The parameter value will be added to the following message template:<br>" +
                        "<b>This is the test email template for your email:<br>'Template Parameter'</b>"
        );
        labels.put("sendTemplate", props);

        //Email with attachment
        props = new HashMap<>();
        props.put("headerText", "Send Email With Attachment");
        props.put("messageLabel", "Message");
        props.put("additionalInfo", "To make sure that you send an attachment with this email, change the value for the 'attachment.invoice' in the application.properties file to the path to the attachment.");
        labels.put("sendAttachment", props);
    }

    @GetMapping(value = {"/send", "/sendTemplate", "/sendAttachment"})
    public String createMail(Model model,
                             HttpServletRequest request) {
        String action = request.getRequestURL().substring(
                request.getRequestURL().lastIndexOf("/") + 1
        );
        Map<String, String> props = labels.get(action);
        Set<String> keys = props.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            model.addAttribute(key, props.get(key));
        }

        model.addAttribute("mailObject", new MailObject());
        logger.info("props : {}" + props);
        logger.info("labels : {}" + labels);
        return "mail/send";
    }

    @PostMapping("/send")
    public String createMail(Model model,
                             @ModelAttribute("mailObject") @Valid MailObject mailObject,
                             Errors errors) {
        if (errors.hasErrors()) {
            logger.info("mailObject : {}" + mailObject.toString());
            logger.info("Error: " + errors.getAllErrors());
            return "mail/send";
        }
        emailService.sendSimpleMessage(mailObject.getTo(),
                mailObject.getSubject(), mailObject.getText());

        logger.info("mailObject : {}" + mailObject.toString());

        return "redirect:/home";

    }

    @PostMapping("/sendTemplate")
    public String createMailWithTemplate(Model model,
                                         @ModelAttribute("mailObject") @Valid MailObject mailObject,
                                         Errors errors) {
        if (errors.hasErrors()) {
            return "mail/send";
        }
        emailService.sendSimpleMessageUsingTemplate(mailObject.getTo(),
                mailObject.getSubject(),
                template,
                mailObject.getText());
        logger.info("mailObject : {}" + mailObject.toString());
        return "redirect:/home";
    }

    @PostMapping(value = "/sendAttachment")
    public String createMailWithAttachment(Model model,
                                           @ModelAttribute("mailObject") @Valid MailObject mailObject,
                                           Errors errors) {
        if (errors.hasErrors()) {
            return "mail/send";
        }
        emailService.sendMessageWithAttachment(
                mailObject.getTo(),
                mailObject.getSubject(),
                mailObject.getText(),
                attachmentPath
        );

        logger.info("mailObject : {}" + mailObject.toString());
        return "redirect:/home";
    }
}
