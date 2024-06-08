package phi.fjpiedade.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferNotAllowedForWalletTypeException extends PicPayException {

    @Override
    public ProblemDetail toProblemDetail() {
        // TODO Auto-generated method stub
        // return super.toProblemDetail();
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("This Wallet Type is not Allowed to Transfer.");

        return pb;
    }

}
