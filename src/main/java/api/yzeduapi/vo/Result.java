package api.yzeduapi.vo;


import api.yzeduapi.constant.ErrorCode;
import lombok.Data;

@Data

public class Result {
	private Integer code;

	private String msg;

	private Object data;
	

	
	public void setErrorCode(ErrorCode errorCode) {
		this.code = errorCode.getCode();
		this.msg = errorCode.getMsg();
	}

	public interface Success {}
	
	public interface Error {}
}
