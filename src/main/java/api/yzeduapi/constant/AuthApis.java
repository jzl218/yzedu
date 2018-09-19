package api.yzeduapi.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
//表示需要认证的api
@AllArgsConstructor
@Getter
@ToString
public enum AuthApis {

	GET_CHOOSEN_COURSE(0,"/api/course/getstudentcourse"),
	GET_COURSE_DETAIL(1,"/api/course/getcourse"),
	GET_COURSE_CHAPTER(2,"/api/course/getchapter"),
	GET_CHAPTER_VIDEO(3,"/api/course/getvideo"),
	GET_CHAPTER_WARE(4,"/api/course/getware"),
	SET_LEARNING(5,"/api/course/setlearned"),
	GET_COURSE_PRACTICE(6,"/api/course/getpractice"),
	POST_PRACTICE(7,"/api/course/practicesubmit"),
	GET_SUBMITTED_PRACTICE(8,"/api/course/getsubmitedpractice"),


	SEND_EMAIL_CODE(9,"/api/message/emailcode"),
	UPDATE_PASS(10,"/api/message/updatepass"),
	SET_EAMIL(11,"api/message/setemail"),

	;

	/** ?~? 表示教师角色权限*/


	private Integer id;
	private String url;
}
