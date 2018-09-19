package api.yzeduapi.exception;

import api.yzeduapi.vo.Result;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class BaseException extends RuntimeException {


	protected Result result;
	
	public BaseException() {
		Result result=new Result();
		this.result=result;
	}
	
	public BaseException(Result result) {
		this();
		this.result = result;
	}
}
