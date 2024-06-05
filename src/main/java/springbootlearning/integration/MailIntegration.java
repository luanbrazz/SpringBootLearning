package springbootlearning.integration;

public interface MailIntegration {
    void sendMail(String to, String message, String subject);
}
