package phi.fjpiedade.picpay.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import phi.fjpiedade.picpay.entity.Transfer;

@FeignClient(name = "notification", url = "${client.notification-service.url}")

public interface NotificationClient {
    @PostMapping
    ResponseEntity<Void> sendNotification(@RequestBody Transfer transfer);
}
