package phi.fjpiedade.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferNotAuthorizedException extends PicPayException {

    @Override
    public ProblemDetail toProblemDetail() {
        // TODO Auto-generated method stub
        // return super.toProblemDetail();
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("This Transfer not Authorized.");
        pb.setDetail("Authorization Service Not Authorized this Transfer.");

        return pb;
    }

}
