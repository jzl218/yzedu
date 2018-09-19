package api.yzeduapi.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor

@Getter
@ToString
public enum ErrorCode {
	
	ACCOUNT_OR_PASSWORD_ERROR(1000, "帐号或密码不正确"),
	COURSE_NOT_FOUND(1001,"找不到课程"),
	ACCOUNT_DENIED(1003,"账户冻结"),
	CHAPTER_NOT_FOUND(1004,"找不到章节"),
	CHAPTER_FAILED_UPDATE(1005,"学习更新失败"),
	PRACTICE_NOT_FONUND(1006,"找不到练习"),
	PRACTICE_SUBMIT_ERROR(1007,"上传练习失败"),
	PRACTICE_NOT_SUBMIT(1008,"练习尚未提交"),
	;

	private Integer code;
	private String msg;


}
