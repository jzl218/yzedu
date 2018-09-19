package api.yzeduapi.exception;

import api.yzeduapi.constant.ErrorCode;


@SuppressWarnings("serial")
public class UserNotFoundException extends BaseException {

	public UserNotFoundException(ErrorCode error) {
		super.result.setErrorCode(error);
	}
}
