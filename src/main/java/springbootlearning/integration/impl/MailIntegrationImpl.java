package springbootlearning.integration.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import springbootlearning.integration.MailIntegration;

@Slf4j
@Component
public class MailIntegrationImpl implements MailIntegration {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(String to, String message, String subject) {

        // Simulating sending the email
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setText(message);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        // Log of details of the email that would be sent
        log.info("Simulando o envio de e-mail...");
        log.info("Para: " + simpleMailMessage.getTo()[0]);
        log.info("Assunto: " + simpleMailMessage.getSubject());
        log.info("Mensagem: " + simpleMailMessage.getText());
    }
}
