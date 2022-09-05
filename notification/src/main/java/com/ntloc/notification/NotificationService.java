package com.ntloc.notification;

import com.ntloc.client.notification.NotificationRequest;
import com.ntloc.notification.aws.AWSEmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.ntloc.notification.NotificationConstant.NOTIFICATION_NOT_FOUND;
import static com.ntloc.notification.NotificationConstant.PJ_AT;

@AllArgsConstructor
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final AWSEmailService emailService;

    public List<NotificationDTO> getAllNotification() {
        List<NotificationEntity> listProduct = notificationRepository.findAll();
        return notificationMapper.toListDTO(listProduct);
    }

    public NotificationDTO getNotification(Long notificationId) {
        NotificationEntity product = notificationRepository.findById(notificationId).orElseThrow(() ->
                new IllegalStateException(NOTIFICATION_NOT_FOUND));
        return notificationMapper.toDTO(product);
    }

    public void sendNotification(NotificationRequest notificationRequest) {
        notificationRepository.save(NotificationEntity.builder()
                .toCustomerId(notificationRequest.getToCustomerId())
                .toCustomerName(notificationRequest.getToCustomerName())
                .toCustomerEmail(notificationRequest.getToCustomerEmail())
                .subject(notificationRequest.getSubject())
                .sender(PJ_AT)
                .message(notificationRequest.getMessage())
                .sentAt(LocalDateTime.now())
                .build());

        emailService.send(notificationRequest.getToCustomerEmail(),
        buildEmail(notificationRequest.getToCustomerName(),
                notificationRequest.getMessage()),notificationRequest.getSubject());

    }

    public String buildEmail(String name, String message) {
        return "<p>Hi " + name + ",</p>"
                + "<p>" + message + ". Have a nice day.</p>"
                + "<p>Best regard,</p>"
                + "<p>PJ-AT team</p>";
    }

}
