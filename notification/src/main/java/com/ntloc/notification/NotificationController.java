package com.ntloc.notification;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ntloc.notification.NotificationConstant.URI_REST_API_VERSION_NOTIFICATION;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = URI_REST_API_VERSION_NOTIFICATION)
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public List<NotificationDTO> getAllNotification() {
        return notificationService.getAllNotification();
    }

    @GetMapping(path = "/{id}")
    public NotificationDTO getNotification(@PathVariable("id") Long id) {
        log.info("NotificationId {}", id);
        return notificationService.getNotification(id);
    }


}
