package api.yzeduapi.web;

import api.yzeduapi.exception.BaseException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(BaseException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Object handleBaseException(BaseException e) {
		logErr(e);
		return e.getResult();
	}
	
	private void logErr(BaseException e) {
		log.debug("errorCode: " + e.getResult().getCode() + ", errorMsg: " + e.getResult().getMsg());
	}


}
