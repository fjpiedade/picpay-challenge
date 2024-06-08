package phi.fjpiedade.picpay.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import phi.fjpiedade.picpay.exception.PicPayException;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(PicPayException.class)
    public ProblemDetail handlePicPayException(PicPayException e) {
        return e.toProblemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        var fieldErrors = e.getFieldErrors()
                .stream()
                .map(field -> new InvalidParam(field.getField(), field.getDefaultMessage()))
                .toList();

        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pb.setTitle("Your request Parameters didn't validate.");
        pb.setProperty("Invalid Params", fieldErrors);

        return pb;
    }

    private record InvalidParam(String name, String reason) {
    };

}
