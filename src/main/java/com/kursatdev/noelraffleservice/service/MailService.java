package com.kursatdev.noelraffleservice.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;

@Service
@AllArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;

    public void sendBasicEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public void sendEmail(String toEmail, String toDisplayName, String receiverDisplayName, String raffleTitle, Locale locale) {
        Context context = new Context();
        context.setVariable("displayName", toDisplayName);
        context.setVariable("receiverDisplayName", receiverDisplayName);
        context.setVariable("raffleTitle", raffleTitle);
        context.setLocale(locale);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {
            helper.setTo(toEmail);
            helper.setSubject((locale.getLanguage().equals("tr") ? "Mutlu Yıllar" : "Happy New Year"));
            String templateName = (locale.getLanguage().equals("tr")) ? "email-template" : "email-template_en";
            String htmlContent = templateEngine.process(templateName, context);
            helper.setText(htmlContent, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendGiftEmail(String toEmail, String toDisplayName, String giftName, String raffleTitle, Locale locale) {
        Context context = new Context();
        context.setVariable("displayName", toDisplayName);
        context.setVariable("giftName", giftName);
        context.setVariable("raffleTitle", raffleTitle);
        context.setLocale(locale);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {
            helper.setTo(toEmail);
            helper.setSubject(raffleTitle + " | " + (locale.getLanguage().equals("tr") ? "Hediye Çekilişi" : "Gift Raffle"));
            String templateName = (locale.getLanguage().equals("tr")) ? "gift-email-template" : "gift-email-template_en";
            String htmlContent = templateEngine.process(templateName, context);
            helper.setText(htmlContent, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
    }

}
