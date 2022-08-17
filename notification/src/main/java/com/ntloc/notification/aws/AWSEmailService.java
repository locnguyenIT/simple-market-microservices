package com.ntloc.notification.aws;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AWSEmailService {

    private final AmazonSimpleEmailService emailService;

    public void send(String to, String content, String subject) {
        try {
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(new Destination().withToAddresses(to))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset("UTF-8").withData(content)))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(subject)))
                    .withSource("ntloc.developer@gmail.com");
            emailService.sendEmail(request);
        } catch (AmazonSimpleEmailServiceException e) {
            throw new IllegalStateException("Failed to send email", e);
        }
    }
}
