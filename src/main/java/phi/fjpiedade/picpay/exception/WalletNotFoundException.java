package phi.fjpiedade.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class WalletNotFoundException extends PicPayException {
    private Long walletId;

    public WalletNotFoundException(Long walletId) {
        this.walletId = walletId;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        // TODO Auto-generated method stub
        // return super.toProblemDetail();
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Wallet Not Found Already Exist.");
        pb.setDetail("There is no wallet with id " + walletId + ".");
        return pb;
    }
}
