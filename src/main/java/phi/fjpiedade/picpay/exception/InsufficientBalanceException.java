package phi.fjpiedade.picpay.exception;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InsufficientBalanceException extends PicPayException {
    private BigDecimal value;

    public InsufficientBalanceException(BigDecimal value) {
        this.value = value;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        // TODO Auto-generated method stub
        // return super.toProblemDetail();
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Insufficient Balance");
        pb.setDetail("You cannot transfer a value bigger than your current balance = " + value + ".");
        return pb;
    }
}
