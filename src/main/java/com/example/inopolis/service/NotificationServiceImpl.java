package com.example.inopolis.service;

import com.example.inopolis.model.dto.NotificationDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationServiceImpl implements NotificationService{

    JavaMailSender mailSender;

    @Async
    @Override
    public void notifyByEmail(NotificationDTO dto) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false);
            helper.setTo(dto.getEmail());
            helper.setSubject(dto.getTema());
            helper.setText(dto.getMessage(), false);
            mailSender.send(message);
            System.out.println("Email sent to " + dto.getEmail());
        } catch (MessagingException e) {
            System.err.println("Failed to send email to " + dto.getEmail());
            e.printStackTrace();
        }
    }
}
