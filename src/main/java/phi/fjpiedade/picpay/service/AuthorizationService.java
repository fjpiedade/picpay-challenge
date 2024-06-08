package phi.fjpiedade.picpay.service;

import org.springframework.stereotype.Service;

import phi.fjpiedade.picpay.client.AuthorizationClient;
import phi.fjpiedade.picpay.client.dto.TransferDTO;
import phi.fjpiedade.picpay.exception.PicPayException;

@Service
public class AuthorizationService {
    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(TransferDTO transfer) {
        var resp = authorizationClient.isAuthorized();

        if (resp.getStatusCode().isError()) {
            throw new PicPayException();
        }
        return resp.getBody().authorized();
    }

}
