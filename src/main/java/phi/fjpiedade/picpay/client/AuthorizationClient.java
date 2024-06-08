package phi.fjpiedade.picpay.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import phi.fjpiedade.picpay.client.dto.AuthorizationResponse;

@FeignClient(name = "authorization", url = "${client.authorization-service.url}")

public interface AuthorizationClient {

    @GetMapping
    ResponseEntity<AuthorizationResponse> isAuthorized();

}