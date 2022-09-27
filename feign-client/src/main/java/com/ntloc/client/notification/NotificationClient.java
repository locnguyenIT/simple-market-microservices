package com.ntloc.client.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification", url = "${client.notification.url}")
public interface NotificationClient {

    @PostMapping(path = "/notification/api/v1/notification")
    void sendNotification(@RequestBody NotificationRequest notificationRequest);

}
