package api.yzeduapi.exception;

import api.yzeduapi.constant.ErrorCode;


@SuppressWarnings("serial")
public class UserErrorException extends BaseException {
	
	public UserErrorException(String msg) {
		super.result.setMsg(msg);
		super.result.setCode(-1);
	}

	
	public UserErrorException(ErrorCode error) {
		super.result.setErrorCode(error);
	}
	

}
