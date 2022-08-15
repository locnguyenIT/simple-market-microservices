package com.ntloc.notification;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ntloc.notification.NotificationConstant.NOTIFICATION_NOT_FOUND;

@AllArgsConstructor
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public List<NotificationDTO> getAllNotification() {
        List<NotificationEntity> listProduct = notificationRepository.findAll();
        return notificationMapper.toListDTO(listProduct);
    }

    public NotificationDTO getNotification(Long notificationId) {
        NotificationEntity product = notificationRepository.findById(notificationId).orElseThrow(() ->
                new IllegalStateException(NOTIFICATION_NOT_FOUND));
        return notificationMapper.toDTO(product);
    }

}
