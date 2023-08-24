package io.igorcossta.email;

import io.igorcossta.token.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Async
    public void sendSimpleMail(String to, String firstName, String lastName, final Token token) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("Account verification");
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setText("""
                    Dear %s %s,
                    Thank you for creating an account with us! To ensure the security of your account and complete the registration process, we require you to verify your email address.

                    Please click on the link below to verify your account:
                    %s

                    If you cannot click the link, please copy and paste it into your web browser's address bar.
                    Please note that this link is valid for the next 24 hours only. After that, you will need to request a new verification link.

                    Best regards,
                    The [Your Company Name] Team
                    """.formatted(firstName, lastName, token.getTokenURL() + token.getToken()));
            javaMailSender.send(message);
        } catch (MailException ex) {
            // todo: save in database or message queue to send later
            throw new EmailFailedToSendException(ex.getMessage());
        }
    }
}
