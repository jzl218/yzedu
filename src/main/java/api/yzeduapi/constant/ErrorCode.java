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
	WRONGQUESTION_NOT_FOUND(1009,"错题未找到"),
	EAMIL_CODE_WRONG(1010,"邮箱验证码错误"),
	UPDATE_MESSAGE_ERROR(1011,"更新密码失败"),
	EAMIL_HAS_BOUNDED(1012,"邮箱已绑定"),
	EMAIL_NOT_BOUNDED(1013,"还未绑定邮箱"),
	EMAIL_SET_ERROR(1014,"邮箱设置失败"),
	;

	private Integer code;
	private String msg;


}
